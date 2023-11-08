/**
 * 
 */
package br.edu.unespar.controle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Window;

import br.edu.unespar.dao.EmprestimoDao;
import br.edu.unespar.dao.impl.EmprestimoDaoImpl;
import br.edu.unespar.modelo.Emprestimo;

/**
 * @author heverton.camargo
 *
 */
public class EmprestimoIndexVM {
	
	private List<Emprestimo> listaEmprestimo = new ArrayList<>();
	
	public List<Emprestimo> getListaEmprestimo(){
		EmprestimoDao emprestimoDao = new EmprestimoDaoImpl();
		this.listaEmprestimo = emprestimoDao.listarTodos();
		return this.listaEmprestimo;
	}
	
	@GlobalCommand
	@NotifyChange("listaEmprestimo")
	public void atualizaListaEmprestimo() {
	}

	@Command
	public void addAlunoOpenModal() {
		try {
			final Window window = (Window) Executions.createComponents("/modal/addaluno.zul", null, null);
	        window.doModal();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Command
	public void addEmprestimoOpenModal() {
		try {
			final Window window = (Window) Executions.createComponents("/modal/addemprestimo.zul", null, null);
	        window.doModal();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Command
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void editarEmprestimoOpenModal(@BindingParam("paramEmprestimo") Emprestimo emprestimo) {
		
		Map parametros = new HashMap<>();
		parametros.put("emprestimoselecionado", emprestimo);
		try {
			final Window window = (Window) Executions.createComponents("/modal/addemprestimo.zul", null, parametros);
	        window.doModal();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Command
	public void devolverLivro(@BindingParam("paramEmprestimo") Emprestimo emprestimo) {
		Messagebox.show("Tem certeza que deseja devolver este livro?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>() {
					
			@Override
			public void onEvent(Event evt) throws Exception {
		        if (evt.getName().equals("onOK")) {
		        	
		        	Calendar agora = Calendar.getInstance();
		        	
		        	emprestimo.setDt_devolucao(agora.getTime());
		        	
		        	emprestimo.getLivro().setStatus_livro(false);
		        	
		        	EmprestimoDao emprestimoDao = new EmprestimoDaoImpl();
		        	
		        	emprestimoDao.salvar(emprestimo);
		        	
		        	BindUtils.postGlobalCommand(null, null, "atualizaListaEmprestimo", null);
		        	
		        }
			}
			
		});
	}
}
