package br.ueg.nutshell.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Entidade de transferência de Fornecedor")
public @Data
class FornecedorDTO implements Serializable {

    @ApiModelProperty(value = "id do Fornecedor")
    private Long id;

    @ApiModelProperty(value = "CPF/CNPJ do Fornecedor")
    private String cpfCnpj;

    @ApiModelProperty(value = "Razao Social do Fornecedor")
    private String razaoSocial;

    @ApiModelProperty(value = "Nome Fantasia do Fornecedor")
    private String nomeFantasia;

    @ApiModelProperty(value = "nome do Tipo de Pessoa do Fornecedor")
    private String nomeTipoPessoa;

    @ApiModelProperty(value = "Indica se o Fornecedor está ativo ou não")
    private Boolean situacao;

}
