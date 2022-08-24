/*
 * UsuarioRepositoryCustom.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.repository;

import java.util.List;

import br.ueg.nutshell.application.dto.FiltroUsuarioDTO;
import br.ueg.nutshell.application.dto.UsuarioDTO;
import br.ueg.nutshell.application.model.Usuario;

/**
 * Classe de persistência referente a entidade {@link Usuario}.
 * 
 * @author UEG
 */
public interface UsuarioRepositoryCustom {

	/**
	 * Retorna a Lista de {@link UsuarioDTO} conforme o login pesquisado.
	 * 
	 * @param login -
	 * @return -
	 */
	public List<UsuarioDTO> findAllByLoginIgnoreCaseContaining(final String login);

	/**
	 * Retorna a Lista de {@link UsuarioDTO} conforme o filtro pesquisado.
	 * 
	 * @param filtroTO -
	 * @return -
	 */
	public List<Usuario> findAllByFiltro(FiltroUsuarioDTO filtroTO);

}
