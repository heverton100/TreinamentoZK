/**
 * 
 */
package br.edu.unespar.dao;

import java.util.List;

import br.edu.unespar.modelo.Emprestimo;

/**
 * @author heverton.camargo
 *
 */
public interface EmprestimoDao {

	void salvar(Emprestimo entidade);
	
	List<Emprestimo> listarTodos();
}
