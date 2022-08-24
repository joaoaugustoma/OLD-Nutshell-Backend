/*
 * GrupoRepositoryCustom.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.repository;


import br.ueg.nutshell.application.dto.FiltroAmigoDTO;
import br.ueg.nutshell.application.model.Amigo;
import br.ueg.nutshell.application.model.Grupo;

import java.util.List;

/**
 * Classe de persistência referente a entidade {@link Grupo}.
 * 
 * @author UEG
 */
public interface AmigoRepositoryCustom {

	/**
	 * Retorna uma lista de {@link Amigo} conforme o filtro de pesquisa informado.
	 * 
	 * @param filtroAmigoDTO
	 * @return
	 */
	public List<Amigo> findAllByFiltro(FiltroAmigoDTO filtroAmigoDTO);



}
