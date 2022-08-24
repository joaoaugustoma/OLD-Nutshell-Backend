/*
 * Usuario.java
 * Copyright (c) UEG.
 */
package br.ueg.nutshell.application.model;

import br.ueg.nutshell.application.configuration.Constante;
import br.ueg.nutshell.application.enums.StatusAtivoInativo;
import br.ueg.nutshell.application.enums.StatusSimNao;
import br.ueg.nutshell.application.enums.converter.StatusAtivoInativoConverter;
import br.ueg.nutshell.application.enums.converter.StatusSimNaoConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * Classe de representação de 'Sistema'.
 *
 * @author UEG
 */
@Entity
// Indica qual a tabela de versionamento será utilzada, opcional se se utilizar o padrão
@Table(name = "TBL_INGREDIENTE", schema = Constante.DATABASE_OWNER)
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name = "TBL_S_INGREDIENTE", sequenceName = "TBL_S_INGREDIENTE", allocationSize = 1, schema = Constante.DATABASE_OWNER)
public @Data class Ingrediente implements Serializable{

	private static final long serialVersionUID = -8899975090870463525L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_S_INGREDIENTE")
	@Column(name = "CODG_INGREDIENTE", nullable = false)
	private Long id;

	@Column(name = "NOME", length = 65, nullable = false)
	private String nome;

	@Column(name = "DATA_ENTRADA", nullable = false)
	private LocalDate dataEntrada;

	@Column(name = "DATA_VALIDADE", nullable = false)
	private LocalDate dataValidade;

	@Convert(converter = StatusAtivoInativoConverter.class)
	@Column(name = "STAT_USUARIO", nullable = false, length = 1)
	private StatusAtivoInativo status;

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<IngredienteTag> tags;


	/**
	 * Verifica se o Status do Usuário é igual a 'Ativo'.
	 *
	 * @return -
	 */
	@Transient
	public boolean isStatusAtivo() {
		return status != null && StatusAtivoInativo.ATIVO.getId().equals(status.getId());
	}

}
