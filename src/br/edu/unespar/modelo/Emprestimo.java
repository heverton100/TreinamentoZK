/**
 * 
 */
package br.edu.unespar.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * @author heverton.camargo
 *
 */
@Data
@Entity
@Table(name="tb_emprestimo", schema="treinamento")
public class Emprestimo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_emprestimo")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="cd_aluno")
	private Aluno aluno;
	
	@OneToOne
	@JoinColumn(name="cd_livro")
	private Livro livro;
	
	@Temporal(TemporalType.DATE)
	@Column(name="emp_dt_emprestimo")
	private Date dt_emprestimo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="emp_dt_devolucao")
	private Date dt_devolucao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="emp_criado_em")
	private Date criadoEm;

}
