/*
 * FornecedorMapper.java
 * Copyright João Augusto Moreira Ananias.
 * 
 *
 *
 *
 */
package br.ueg.nutshell.application.mapper;

import br.ueg.nutshell.application.dto.FornecedorDTO;
import br.ueg.nutshell.application.model.Fornecedor;
import org.mapstruct.Mapper;

/**
 * Classe adapter referente a entidade {@link Fornecedor}.
 *
 * @author João Augusto Moreira Ananias
 */
@Mapper(componentModel = "spring", uses = { StatusAtivoInativoMapper.class, SimNaoMapper.class, TipoPessoaMapper.class})
public interface FornecedorMapper {
	/**
	 * Converte a entidade {@link Fornecedor} em DTO {@link FornecedorDTO}
	 *
	 * @param fornecedor
	 * @return
	 */

	public FornecedorDTO toDTO(Fornecedor fornecedor);

	/**
	 * Converte o DTO {@link FornecedorDTO} para entidade {@link Fornecedor}
	 *
	 * @param fornecedorDTO
	 * @return
	 */
	public Fornecedor toEntity(FornecedorDTO fornecedorDTO);
}
