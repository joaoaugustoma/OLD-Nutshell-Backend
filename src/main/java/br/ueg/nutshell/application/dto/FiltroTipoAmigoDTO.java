package br.ueg.nutshell.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Dados do filtro de pesquisa de Tipo Amigo")
public @Data class FiltroTipoAmigoDTO implements Serializable {
    @ApiModelProperty(value = "Nome do Grupo")
    private String nome;
}
