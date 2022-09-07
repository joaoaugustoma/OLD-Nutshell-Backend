/*
 * GrupoRepositoryImpl.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.repository.impl;

import br.ueg.nutshell.application.dto.FiltroFornecedorDTO;
import br.ueg.nutshell.application.dto.FornecedorDTO;
import br.ueg.nutshell.application.model.Fornecedor;
import br.ueg.nutshell.application.repository.FornecedorRepositoryCustom;
import br.ueg.nutshell.comum.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe de persistência referente a entidade {@link Fornecedor}.
 *
 * @author João Augusto Moreira Ananias
 */
@Repository
public class FornecedorRepositoryImpl implements FornecedorRepositoryCustom {

	@Autowired
	private EntityManager entityManager;
	
	/**
	 * @see FornecedorRepositoryCustom#findAllByFiltro(FiltroFornecedorDTO)
	 * @return
	 */
	@Override
	public List<Fornecedor> findAllByFiltro(FiltroFornecedorDTO filtroDTO) {
		Map<String, Object> parametros = new HashMap<>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT DISTINCT fornecedor FROM Fornecedor fornecedor");
		jpql.append(" WHERE 1=1 ");
		
		if (!Util.isEmpty(filtroDTO.getRazaoSocial())) {
			jpql.append(" AND fornecedor.razaoSocial = :razaoSocial ");
			parametros.put("login", filtroDTO.getRazaoSocial());
		}
		if (!Util.isEmpty(filtroDTO.getCpfCnpj())) {
			jpql.append(" AND fornecedor.cpfCnpj = :cpfCnpj ");
			parametros.put("cpfCnpj", filtroDTO.getCpfCnpj());
		}
		if (filtroDTO.getPessoaEnum() != null) {
			jpql.append(" AND fornecedor.tipoPessoa = :tipoPessoa ");
			parametros.put("tipoPessoa", filtroDTO.getPessoaEnum());
		}
		if (filtroDTO.getStatusEnum() != null) {
			jpql.append(" AND fornecedor.situacao = :situacao ");
			parametros.put("situacao", filtroDTO.getStatusEnum());
		}

		jpql.append(" ORDER BY fornecedor.razaoSocial ASC ");

		TypedQuery<Fornecedor> query = entityManager.createQuery(jpql.toString(), Fornecedor.class);
		parametros.entrySet().forEach(parametro -> query.setParameter(parametro.getKey(), parametro.getValue()));
		return query.getResultList();
	}
	
}
