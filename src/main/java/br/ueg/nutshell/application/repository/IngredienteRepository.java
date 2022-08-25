/*
 * IngredienteRepository.java
 * Copyright (c) João Augusto Moreira Ananias.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.repository;

import br.ueg.nutshell.application.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Classe de persistência referente a entidade {@link Ingrediente}.
 *
 * @author UEG
 */
@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
	// TODO Garantir que somente usuários com ID_ORIGEM_CADASTRO = PORTALSSO

	/**
	 * Retorna a instância do {@link Ingrediente} conforme o 'login' informado.
	 * 
	 * @param login
	 * @return
	 */
	public Ingrediente findByLogin(final String login);

	/**
	 * Retorna a instância do {@link Ingrediente} conforme o 'cpf' informado.
	 * 
	 * @param cpf
	 * @return
	 */
	public Ingrediente findByCpf(final String cpf);

	/**
	 * Retorna a instância do {@link Ingrediente} conforme o 'cpf' informado
	 * e que não tenha o 'id' informado.
	 * 
	 * @param cpf
	 * @param id
	 * @return
	 */
	@Query(" SELECT usuario FROM Ingrediente usuario "
			+ " WHERE usuario.cpf = :cpf "
			+ " AND (:id IS NULL OR usuario.id != :id)")
	public Ingrediente findByCpfAndNotId(@Param("cpf") final String cpf, @Param("id") final Long id);

	/**
	 * Retorna uma instância de {@link Ingrediente} conforme o 'id' informado.
	 * 
	 * @param id
	 * @return
	 */
	@Query(" SELECT usuario FROM Ingrediente usuario LEFT JOIN FETCH usuario.grupos usuarioGrupo "
			+ " LEFT JOIN FETCH usuarioGrupo.grupo grupo "
			+ " LEFT JOIN FETCH usuario.telefones telefone "
			+ " WHERE usuario.id = :id ")
	public Optional<Ingrediente> findByIdFetch(@Param("id") final Long id);


	/**
	 * Retorna o total de Usuários encontrados na base de dados conforme o cpf
	 * informado.
	 *
	 * @param cpf cpf do usuário
	 * @return
	 */
	public Long countByCpf(final String cpf);

}
