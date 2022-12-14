/*
 * AuthenticationProviderImpl.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.security;

import br.ueg.nutshell.api.security.AuthenticationProvider;
import br.ueg.nutshell.application.dto.CredencialDTO;
import br.ueg.nutshell.application.service.AuthService;
import br.ueg.nutshell.comum.exception.BusinessException;
import br.ueg.nutshell.comum.security.Credential;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por prover a instância de {@link Authentication} com as
 * credenciais do Usuário logado.
 * 
 * @author UEG
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private AuthService authService;

	/**
	 * @see AuthenticationProvider#getAuthentication(String)
	 */
	@Override
	public Credential getAuthentication(final String accessToken) {
		Credential credential = null;

		try {
			CredencialDTO credencialDTO = authService.getInfoByToken(accessToken);

			if (credencialDTO != null) {
				credential = CredentialImpl.newInstance(credencialDTO, accessToken);
			}
		} catch (BusinessException e) {
			logger.error("Acesso negado.", e);
		}
		return credential;
	}

}
