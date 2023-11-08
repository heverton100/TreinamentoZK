/**
 * 
 */
package br.edu.unespar.dao;

import java.util.List;

import br.edu.unespar.modelo.Livro;

/**
 * @author heverton.camargo
 *
 */
public interface LivroDao {

	void salvar(Livro entidade);
	
	List<Livro> listarTodos();
	
	List<Livro> listarLivrosDisponiveis();
}
