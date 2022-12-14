/*
 * MessageResponse.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.nutshell.comum.exception;

import br.ueg.nutshell.comum.util.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de representação de Mensagem de Resposta utilizada nas implementações
 * 'ExceptionHandler'
 * 
 * @author UEG
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageResponse implements Serializable {

	private static final long serialVersionUID = 4878825827657916191L;

	@ApiModelProperty(value = "Código da Mensagem")
	private String code;

	@ApiModelProperty(value = "Status HTTP")
	private Integer status;

	@ApiModelProperty(value = "Descrição erro HTTP")
	private String error;

	@ApiModelProperty(value = "Mensagem de negócio")
	private String message;

	@ApiModelProperty(value = "Parâmetros da mensagem")
	private Object[] parameters;

	@ApiModelProperty(value = "Atributos de validação")
	private List<FieldResponse> attributes;

	/**
	 * Adiciona a instância de {@link FieldResponse}.
	 * 
	 * @param field -
	 */
	public void addAttribute(final FieldResponse field) {
		if (CollectionUtil.isEmpty(attributes)) {
			attributes = new ArrayList<FieldResponse>();
		}
		attributes.add(field);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the parameters
	 */
	public Object[] getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the attributes
	 */
	public List<FieldResponse> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(List<FieldResponse> attributes) {
		this.attributes = attributes;
	}

}
