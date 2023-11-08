/**
 * 
 */
package br.edu.unespar.controle;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import br.edu.unespar.dao.LivroDao;
import br.edu.unespar.dao.impl.LivroDaoImpl;
import br.edu.unespar.modelo.Livro;
import lombok.Getter;
import lombok.Setter;

/**
 * @author heverton.camargo
 *
 */
public class AddLivroIndexVM {
	
	public AddLivroIndexVM() {
		if(Executions.getCurrent().getSession().getAttribute("livroselecionado") != null) {
			this.setFormLivro((Livro) Executions.getCurrent().getSession().getAttribute("livroselecionado"));
		}
		if(Executions.getCurrent().getArg().get("livroselecionado") != null) {
			this.setFormLivro((Livro) Executions.getCurrent().getArg().get("livroselecionado"));
		}
	}

	@Getter @Setter
	private Livro formLivro = new Livro();
	
	@Command
	@NotifyChange({"formLivro"})
	public void salvarLivro(@BindingParam("janela") Window janela) {
		
		LivroDao livroDao = new LivroDaoImpl();
	
		try {
			System.out.println(formLivro.getStatus_livro());
			livroDao.salvar(this.formLivro);
			this.formLivro = new Livro();
			BindUtils.postGlobalCommand(null, null, "atualizaListaLivro", null);
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
