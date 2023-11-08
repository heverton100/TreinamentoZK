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
@Table(name="tb_aluno", schema="treinamento")
public class Aluno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_aluno")
	private Long id;
	
	@Column(name="aln_nome")
	private String nome;
	
	@Column(name="aln_matricula")
	private String matricula;
	
	@Temporal(TemporalType.DATE)
	@Column(name="aln_dt_nascimento")
	private Date dt_nascimento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="aln_criado_em")
	private Date criadoEm;

}
