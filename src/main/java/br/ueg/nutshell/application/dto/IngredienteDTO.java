/*
 * UsuarioDTO.java  
 * Copyright UEG.
 * 
 *
 *
 *
 */
package br.ueg.nutshell.application.dto;

import br.ueg.nutshell.application.enums.StatusAtivoInativo;
import br.ueg.nutshell.application.model.IngredienteTag;
import br.ueg.nutshell.application.model.Usuario;
import br.ueg.nutshell.comum.util.Util;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Classe de transferência referente a entidade {@link Usuario}.
 *
 * @author UEG
 */
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Entidade de transferência de Usuario")
public @Data class IngredienteDTO implements Serializable {

	private static final long serialVersionUID = -3145730384721847808L;

	@ApiModelProperty(value = "Código do Ingrediente")
	private String id;

	@JsonFormat(shape = Shape.STRING)
	@ApiModelProperty(value = "Data de entrada do Ingrediente")
	private LocalDate dataEntrada;

	@JsonFormat(shape = Shape.STRING)
	@ApiModelProperty(value = "Data de Validade do Ingrediente")
	private LocalDate dataValidade;

	@Size(max = 65)
	@ApiModelProperty(value = "Nome do Ingrediente")
	private String nome;

	@ApiModelProperty(value = "Código do Status do Ingrediente")
	private boolean status;

	@ApiModelProperty(value = "Tags do Ingrediente")
	private List<IngredienteTagDTO> tags;


	@JsonIgnore
	@ApiModelProperty(hidden = true)
	private Long idUsuarioLogado;

	public IngredienteDTO(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	/**
	 * @return the id
	 */
	@JsonIgnore
	public Long getIdLong() {
		Long idLong = null;

		if (!Util.isEmpty(id)) {
			idLong = Long.parseLong(id);
		}
		return idLong;
	}
}
