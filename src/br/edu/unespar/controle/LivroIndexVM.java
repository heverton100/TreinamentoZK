/**
 * 
 */
package br.edu.unespar.controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import br.edu.unespar.dao.LivroDao;
import br.edu.unespar.dao.impl.LivroDaoImpl;
import br.edu.unespar.modelo.Livro;

/**
 * @author heverton.camargo
 *
 */
public class LivroIndexVM {
	
	private List<Livro> listaLivro = new ArrayList<>();
	
	public List<Livro> getListaLivro(){
		LivroDao livroDao = new LivroDaoImpl();
		this.listaLivro = livroDao.listarTodos();
		return this.listaLivro;
	}
	
	@GlobalCommand
	@NotifyChange("listaLivro")
	public void atualizaListaLivro() {
	}
	
	
	@Command
	public void addLivroOpenModal() {
		try {
			final Window window = (Window) Executions.createComponents("/modal/addlivro.zul", null, null);
	        window.doModal();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Command
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void editarLivroOpenModal(@BindingParam("paramLivro") Livro livro) {
		
		Map parametros = new HashMap<>();
		parametros.put("livroselecionado", livro);
		try {
			final Window window = (Window) Executions.createComponents("/modal/addlivro.zul", null, parametros);
	        window.doModal();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
