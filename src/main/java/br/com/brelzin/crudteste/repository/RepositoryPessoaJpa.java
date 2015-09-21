package br.com.brelzin.crudteste.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.brelzin.crudteste.modelo.Pessoa;

@Transactional
@Repository
public class RepositoryPessoaJpa {
	
	@Autowired
	private EntityManager entityManager;

	public int countListaServidores(int cmCampusId, String dsNomeCompleto){
		Query query = entityManager.createQuery("SELECT pessoa "
		+ "FROM Pessoa AS pessoa "  
//		+ ", IN(cargoPessoa.cmServidor.lotacaoServidor) AS lotacaoServidor "  
		+ "WHERE 1 = 1 "
		+ " AND ( :paramUsuario = '' OR pessoa.usuario like :paramUsuario ) "
		);
		query.setParameter("paramUsuario", "%"+dsNomeCompleto.toUpperCase()+"%");
		
		return query.getMaxResults();
	}

	public List<Pessoa> listarUsuarios(String dsNomeCompleto, int pagina){
		pagina--;
		int limit = 20;
		int primeiroRegistro = pagina * limit;

		//use o import javax.persistence.Query
		Query query = entityManager.createQuery("SELECT pessoa "
		+ "FROM Pessoa AS pessoa "  
//		+ ", IN(cargoPessoa.cmServidor.lotacaoServidor) AS lotacaoServidor "  
		+ "WHERE 1 = 1 "
		+ " AND ( :paramUsuario = '' OR pessoa.usuario like :paramUsuario ) "
		);
		query.setParameter("paramUsuario", "%"+dsNomeCompleto.toUpperCase()+"%");
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(limit);
		
		List<Pessoa> list = query.getResultList();
		
		entityManager.close();
		
		return list;
	}
	
	
	public List<Pessoa> listarUsuariosFiltrado(String dsNomeCompleto, int pagina){
		pagina--;
		int limit = 20;
		int primeiroRegistro = pagina * limit;

		//use o import javax.persistence.Query
		Query query = entityManager.createQuery(""
		+ "SELECT pessoa.id, "
		+ "		  pessoa.usuario "
		+ "FROM Pessoa AS pessoa "  
//		+ ", IN(cargoPessoa.cmServidor.lotacaoServidor) AS lotacaoServidor "  
		+ "WHERE 1 = 1 "
		+ " AND ( :paramUsuario = '' OR pessoa.usuario like :paramUsuario ) "
		);
		query.setParameter("paramUsuario", "%"+dsNomeCompleto.toUpperCase()+"%");
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(limit);
		
		List<Object[]> listResult = query.getResultList();

		List<Pessoa> listFiltrado = new ArrayList<Pessoa>();
		Pessoa pessoa = null;

		for (Object[] obj : listResult) {
			pessoa = new Pessoa();
			pessoa.setId( (Integer) obj[0] );
			pessoa.setUsuario( (String) obj[1] );
			
			listFiltrado.add(pessoa);			
		}
		
		entityManager.close();
		
		return listFiltrado;
	}
	
	public List<Pessoa> listaComSelectFiltrado(){
		
		String dsNomeCompleto = "bruno";
		
		Query query = entityManager.createQuery(""
		+ "SELECT pessoa.id, "
		+ "		  pessoa.usuario,"
		+ "		  pessoa.unidade.nome "
		+ "FROM Pessoa AS pessoa "  
		+ "WHERE 1 = 1 "
		+ " AND ( :paramUsuario = '' OR pessoa.usuario like :paramUsuario ) "
		);
		query.setParameter("paramUsuario", "%"+dsNomeCompleto.toUpperCase()+"%");
		
		List<Pessoa> listResult = query.getResultList();
		
		entityManager.close();
		
		return listResult;		
	}
	
	public List<Pessoa> listandoUsandoQueryNativa(){
		
		String dsNomeCompleto = "bruno";
		
		String sql = "SELECT pessoa.id as pessoa_id, "
				+ "		  pessoa.usuario,"
				+ "		  unidade.id, "
				+ "		  unidade.nome "
				+ "FROM pessoa inner join unidade ON pessoa.unidade_fk_id = unidade.id "  
				+ "WHERE 1 = 1 "
				+ " AND ( pessoa.usuario like :paramUsuario ) ";
		
		Query query = entityManager.createNativeQuery( sql );
		query.setParameter("paramUsuario", "%"+dsNomeCompleto.toUpperCase()+"%");
		
		
		List<Object[]> listResult = query.getResultList();

		List<Pessoa> listFiltrado = new ArrayList<Pessoa>();
		Pessoa pessoa = null;

		for (Object[] obj : listResult) {
			pessoa = new Pessoa();
			pessoa.setId( (Integer) obj[0] );
			pessoa.setUsuario( (String) obj[1] );
			
			listFiltrado.add(pessoa);			
		}
		
		entityManager.close();
		
		return listFiltrado;		
	}
	
}
