/**
 * 
 */
package br.edu.unespar.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.unespar.dao.Dao;
import br.edu.unespar.dao.EmprestimoDao;
import br.edu.unespar.modelo.Emprestimo;

/**
 * @author heverton.camargo
 *
 */
public class EmprestimoDaoImpl extends Dao implements EmprestimoDao{

	@Override
	public void salvar(Emprestimo entidade) {
		
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
	public List<Emprestimo> listarTodos() {
		
		EntityManager entityManager = getEntityManager();
		TypedQuery<Emprestimo> consulta = entityManager.createQuery(
				"SELECT e FROM Emprestimo e", Emprestimo.class);
		return consulta.getResultList();
		
	}

}
