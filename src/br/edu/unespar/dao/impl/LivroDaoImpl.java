/**
 * 
 */
package br.edu.unespar.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.unespar.dao.Dao;
import br.edu.unespar.dao.LivroDao;
import br.edu.unespar.modelo.Livro;

/**
 * @author heverton.camargo
 *
 */
public class LivroDaoImpl extends Dao implements LivroDao{

	@Override
	public void salvar(Livro entidade) {

		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			Calendar agora = Calendar.getInstance();
			if(entidade.getId() == null) {
				entidade.setCriadoEm(agora.getTime());
				entityManager.persist(entidade);
			}
			else {
				entityManager.merge(entidade);
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Livro> listarTodos() {
		
		EntityManager entityManager = getEntityManager();
		TypedQuery<Livro> consulta = entityManager.createQuery(
				"SELECT l FROM Livro l", Livro.class);
		return consulta.getResultList();
		
	}
	
	@Override
	public List<Livro> listarLivrosDisponiveis() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Livro> consulta = entityManager.createQuery(
				"SELECT l FROM Livro l WHERE l.status_livro = false", Livro.class);
		return consulta.getResultList();
	}

}
