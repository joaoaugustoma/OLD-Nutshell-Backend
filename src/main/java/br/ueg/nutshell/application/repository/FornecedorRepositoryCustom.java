/*
 * GrupoRepositoryCustom.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.repository;
import br.ueg.nutshell.application.dto.FiltroFornecedorDTO;
import br.ueg.nutshell.application.model.Fornecedor;

import java.util.List;

/**
 * Classe de persistência referente a entidade {@link Fornecedor}.
 * 
 * @author João Augusto Moreira Ananias
 */
public interface FornecedorRepositoryCustom {

	/**
	 * Retorna uma lista de {@link Fornecedor} conforme o filtro de pesquisa informado.
	 * 
	 * @param filtroFornecedorDTO
	 * @return
	 */
	public List<Fornecedor> findAllByFiltro(FiltroFornecedorDTO filtroFornecedorDTO);



}
