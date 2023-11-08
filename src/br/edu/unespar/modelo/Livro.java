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
@Table(name="tb_livro", schema="treinamento")
public class Livro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_livro")
	private Long id;
	
	@Column(name="lvr_titulo")
	private String titulo;
	
	@Column(name="lvr_ano")
	private Integer ano;
	
	@Column(name="lvr_isbn")
	private String isbn;
	
	@Column(name="lvr_status")
	private Boolean status_livro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lvr_criado_em")
	private Date criadoEm;
	
	
}
