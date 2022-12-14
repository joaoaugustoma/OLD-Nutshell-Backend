/*
 * SwaggerConfig.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.configuration;

import br.ueg.nutshell.api.config.ApiSwaggerConfig;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe de configuração referente a geração de documentação automatida da API
 * REST.
 * 
 * @author UEG
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends ApiSwaggerConfig {

}
