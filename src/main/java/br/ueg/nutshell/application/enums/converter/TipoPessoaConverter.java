/*
 * StatusAtivoInativoConverter.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.enums.converter;

import br.ueg.nutshell.application.enums.TipoPessoa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Classe de conversão JPA referente ao enum {@link TipoPessoa}.
 * 
 * @author UEG
 */
@Converter(autoApply = true)
public class TipoPessoaConverter implements AttributeConverter<TipoPessoa, String> {

	/**
	 * @see AttributeConverter#convertToDatabaseColumn(Object)
	 */
	@Override
	public String convertToDatabaseColumn(TipoPessoa tipoPessoa) {
		return tipoPessoa != null ? tipoPessoa.getId() : null;
	}

	/**
	 * @see AttributeConverter#convertToEntityAttribute(Object)
	 */
	@Override
	public TipoPessoa convertToEntityAttribute(String id) {
		return TipoPessoa.getById(id);
	}

}
