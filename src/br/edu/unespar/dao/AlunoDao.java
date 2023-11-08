/**
 * 
 */
package br.edu.unespar.dao;

import java.util.List;

import br.edu.unespar.modelo.Aluno;

/**
 * @author heverton.camargo
 *
 */
public interface AlunoDao {

	void salvar(Aluno entidade);
	
	List<Aluno> listarTodos();
	
}
