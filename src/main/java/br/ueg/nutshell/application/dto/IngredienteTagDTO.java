/*
 * UsuarioTO.java  
 * Copyright UEG.
 * 
 *
 *
 *
 */
package br.ueg.nutshell.application.dto;

import br.ueg.nutshell.application.model.Ingrediente;
import br.ueg.nutshell.application.model.Tag;
import br.ueg.nutshell.application.model.UsuarioGrupo;
import br.ueg.nutshell.comum.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Classe de transferência referente a entidade {@link UsuarioGrupo}.
 *
 * @author UEG
 */
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Entidade de transferência de Usuario Grupos")
public @Data class IngredienteTagDTO implements Serializable {

	private static final long serialVersionUID = -813015525141429296L;

	@ApiModelProperty(value = "Código do Usuário Grupo")
	private String id;

	@ApiModelProperty(value = "Código do Usuário")
	private String idIngrediente;

	@ApiModelProperty(value = "Código do Grupo")
	private String idTag;

	@ApiModelProperty(value = "Nome do Grupo")
	private String nomeTag;

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
