/**
 * 
 */
package br.edu.unespar.controle;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import br.edu.unespar.dao.AlunoDao;
import br.edu.unespar.dao.EmprestimoDao;
import br.edu.unespar.dao.LivroDao;
import br.edu.unespar.dao.impl.AlunoDaoImpl;
import br.edu.unespar.dao.impl.EmprestimoDaoImpl;
import br.edu.unespar.dao.impl.LivroDaoImpl;
import br.edu.unespar.modelo.Aluno;
import br.edu.unespar.modelo.Emprestimo;
import br.edu.unespar.modelo.Livro;
import lombok.Getter;
import lombok.Setter;

/**
 * @author heverton.camargo
 *
 */
public class AddEmprestimoIndexVM extends SelectorComposer<Window> {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	public AddEmprestimoIndexVM() {
		if(Executions.getCurrent().getSession().getAttribute("emprestimoselecionado") != null) {
			this.setFormEmprestimo((Emprestimo) Executions.getCurrent().getSession().getAttribute("emprestimoselecionado"));
		}
		if(Executions.getCurrent().getArg().get("emprestimoselecionado") != null) {
			this.setFormEmprestimo((Emprestimo) Executions.getCurrent().getArg().get("emprestimoselecionado"));
		}
	}
	
	private ListModelList<Aluno> alunoModel = new ListModelList<Aluno>(getListaAluno());
	
	public ListModelList<Aluno> getAlunoModel() {
		return alunoModel;
	}
	
	private ListModelList<Livro> livroModel = new ListModelList<Livro>(getListaLivro());
	
	public ListModelList<Livro> getLivroModel() {
		return livroModel;
	}
	
	@Getter @Setter
	private Emprestimo formEmprestimo = new Emprestimo();
	
	private List<Aluno> listaAluno = new ArrayList<>();
	
	private List<Livro> listaLivro = new ArrayList<>();
	
	public List<Aluno> getListaAluno(){
		AlunoDao alunoDao = new AlunoDaoImpl();
		this.listaAluno = alunoDao.listarTodos();
		return this.listaAluno;
	}
	
	public List<Livro> getListaLivro(){
		LivroDao livroDao = new LivroDaoImpl();
		this.listaLivro = livroDao.listarLivrosDisponiveis();
		return this.listaLivro;
	}
	
	@Command
	@NotifyChange({"formEmprestimo"})
	public void salvarEmprestimo(@BindingParam("janela") Window janela) {
		
		EmprestimoDao emprestimoDao = new EmprestimoDaoImpl();
		
		try {
			formEmprestimo.getLivro().setStatus_livro(true);
			emprestimoDao.salvar(this.formEmprestimo);
			this.formEmprestimo = new Emprestimo();
			BindUtils.postGlobalCommand(null, null, "atualizaListaEmprestimo", null);
			janela.detach();
		} catch (Exception e) {
			Messagebox.show(e.getMessage());
		}
		
	}
	
	@Command
	public void cancelar(@BindingParam("janela") Window janela) {
		janela.detach();
	}
	


}
