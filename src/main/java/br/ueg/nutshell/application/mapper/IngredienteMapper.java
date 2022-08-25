/*
 * IngredienteMapper.java
 * Copyright UEG.
 * 
 *
 *
 *
 */
package br.ueg.nutshell.application.mapper;

import br.ueg.nutshell.application.dto.IngredienteDTO;
import br.ueg.nutshell.application.model.Ingrediente;
import org.mapstruct.Mapper;

/**
 * Classe adapter referente a entidade {@link Ingrediente}.
 *
 * @author João Augusto Moreira Ananias
 */
@Mapper(componentModel = "spring", uses = { StatusAtivoInativoMapper.class, IngredienteMapper.class })
public interface IngredienteMapper {
	/**
	 * Converte a entidade {@link Ingrediente} em DTO {@link IngredienteDTO}
	 *
	 * @param ingrediente
	 * @return
	 */

	IngredienteDTO toDTO(Ingrediente ingrediente);

	/**
	 * Converte o DTO {@link IngredienteDTO} para entidade {@link Ingrediente}
	 *
	 * @param ingredienteDTO
	 * @return
	 */
	public Ingrediente toEntity(IngredienteDTO ingredienteDTO);
}
