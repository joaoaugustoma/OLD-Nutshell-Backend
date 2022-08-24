/*
 * UsuarioGrupo.java  
 * Copyright UEG.
 * 
 *
 *
 *
 */
package br.ueg.nutshell.application.model;

import br.ueg.nutshell.application.configuration.Constante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TBL_INGREDIENTE_TAG", schema = Constante.DATABASE_OWNER)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "TBL_S_INGREDIENTE_TAG", sequenceName = "TBL_S_INGREDIENTE_TAG", allocationSize = 1, schema = Constante.DATABASE_OWNER)
public @Data class IngredienteTag implements Serializable {

	private static final long serialVersionUID = -8899975090870463525L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_S_INGREDIENTE_TAG")
	@Column(name = "ID_INGREDIENTE_TAG", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODG_INGREDIENTE", referencedColumnName = "CODG_INGREDIENTE", nullable = false)
	private Ingrediente ingrediente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TAG", referencedColumnName = "ID_TAG", nullable = false)
	private Tag tag;
}
