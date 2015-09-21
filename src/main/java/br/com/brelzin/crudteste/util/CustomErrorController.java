package br.com.brelzin.crudteste.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.brelzin.crudteste.util.RetornoDto.StatusRetorno;

/**
 * Implementação para substituir a mensagem de erro padrão e exibir no padrão de retorno do IFTM 
 * 
 * @author Bruno Lima
 *
 */
@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

//    @Value("${debug}")
    private boolean debug = true;

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    RetornoDto<Map<String, Object>> error(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> errorAttributes = getErrorAttributes(request, debug);
    	
    	RetornoDto<Map<String, Object>> retorno = new RetornoDto<Map<String, Object>>();
//    	retorno.setStatus("erro");
    	retorno.setStatus(StatusRetorno.erro);
    	retorno.setObj( errorAttributes );
    	retorno.setMsg("ERRO " + errorAttributes.get("status") + ": " + errorAttributes.get("error") );
    	
        return retorno;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

}