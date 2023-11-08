/**
 * 
 */
package br.edu.unespar.controle;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zul.Window;

import br.edu.unespar.dao.AlunoDao;
import br.edu.unespar.dao.impl.AlunoDaoImpl;
import br.edu.unespar.modelo.Aluno;
import lombok.Getter;
import lombok.Setter;

/**
 * @author heverton.camargo
 *
 */
public class AddAlunoIndexVM {
	
	@Getter @Setter
	private Aluno formAluno = new Aluno();
	
	@Command
	@NotifyChange({"formAluno"})
	public void salvarAluno(@BindingParam("janela") Window janela) {
		
		AlunoDao alunoDao = new AlunoDaoImpl();
		
		try {
			alunoDao.salvar(this.formAluno);
			this.formAluno = new Aluno();
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
