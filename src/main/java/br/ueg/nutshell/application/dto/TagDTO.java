/*
 * UsuarioTO.java  
 * Copyright UEG.
 * 
 *
 *
 *
 */
package br.ueg.nutshell.application.dto;

import br.ueg.nutshell.application.enums.StatusAtivoInativo;
import br.ueg.nutshell.application.model.Grupo;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * Classe de transferência referente a entidade {@link Grupo}.
 *
 * @author UEG
 */
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Entidade de transferência de Grupo")
public @Data class TagDTO implements Serializable {

	private static final long serialVersionUID = -4907983765144204384L;

	@ApiModelProperty(value = "Código da Tag")
	private Long id;

	@Size(max = 50)
	@ApiModelProperty(value = "Nome da Tag")
	private String nome;

	@Size(max = 200)
	@ApiModelProperty(value = "Descricao da Tag")
	private String descricao;

	@ApiModelProperty(value = "Código do Status da Tag")
	private boolean status;
}
