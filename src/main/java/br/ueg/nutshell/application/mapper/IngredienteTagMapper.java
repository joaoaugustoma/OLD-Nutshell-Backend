/*
 * IngredienteTagMapper.java
 * Copyright UEG.
 * 
 *
 *
 *
 */
package br.ueg.nutshell.application.mapper;

import br.ueg.nutshell.application.dto.IngredienteTagDTO;
import br.ueg.nutshell.application.model.Ingrediente;
import br.ueg.nutshell.application.model.IngredienteTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Classe adapter referente a entidade {@link IngredienteTag}.
 *
 * @author João Augusto Moreira Ananias
 */
@Mapper(componentModel = "spring", uses = { SimNaoMapper.class, IngredienteMapper.class, TagMapper.class })
public interface IngredienteTagMapper {
	/**
	 * Converte a entidade {@link IngredienteTag} em DTO {@link IngredienteTagDTO}.
	 *
	 * @param ingrediente
	 * @return
	 */
	@Mapping(source = "ingrediente.id", target = "idIngrediente")
	@Mapping(source = "tag.id", target = "idTag")
	@Mapping(source = "tag.nome", target = "nomeTag")
	public IngredienteTagDTO toDTO(IngredienteTag ingrediente);

	/**
	 * Converte o DTO {@link IngredienteTagDTO} para entidade {@link IngredienteTag}.
	 *
	 * @param ingredienteDTO
	 * @return
	 */
	@Mapping(source = "ingredienteDTO.idUsuario", target = "usuario.id")
	@Mapping(source = "ingredienteDTO.idGrupo", target = "grupo.id")
	public IngredienteTag toEntity(IngredienteTagDTO ingredienteDTO);
}
