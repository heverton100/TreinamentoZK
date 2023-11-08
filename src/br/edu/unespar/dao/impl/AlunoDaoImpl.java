/**
 * 
 */
package br.edu.unespar.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.unespar.dao.AlunoDao;
import br.edu.unespar.dao.Dao;
import br.edu.unespar.modelo.Aluno;

/**
 * @author heverton.camargo
 *
 */
public class AlunoDaoImpl extends Dao implements AlunoDao{

	@Override
	public void salvar(Aluno entidade) {
		
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
	public List<Aluno> listarTodos() {
		
		EntityManager entityManager = getEntityManager();
		TypedQuery<Aluno> consulta = entityManager.createQuery(
				"SELECT a FROM Aluno a", Aluno.class);
		return consulta.getResultList();
		
	}

}
