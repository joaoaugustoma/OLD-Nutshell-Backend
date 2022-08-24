package br.ueg.nutshell.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Entidade de transferência de Estatística de Grupo")
public @Data
class GrupoEstatisticasDTO implements Serializable {

    @ApiModelProperty(value = "id do Grupo")
    private Long id;

    @ApiModelProperty(value = "Nome do Grupo")
    private String nome;

    @ApiModelProperty(value = "Descrição do Grupo")
    private String descricao;

    @ApiModelProperty(value = "Total de Usuários do grupo")
    private Long totalUsuarios;

}
