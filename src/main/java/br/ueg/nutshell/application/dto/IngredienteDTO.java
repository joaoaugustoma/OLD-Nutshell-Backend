/*
 * UsuarioDTO.java  
 * Copyright UEG.
 * 
 *
 *
 *
 */
package br.ueg.nutshell.application.dto;

import br.ueg.nutshell.application.model.Ingrediente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe de transferência referente a entidade {@link Ingrediente}.
 *
 * @author João Augusto Moreira Ananias
 */
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Entidade de transferência de Ingrediente")
public @Data class IngredienteDTO implements Serializable {

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

}
