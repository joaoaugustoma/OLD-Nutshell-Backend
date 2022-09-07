package br.ueg.nutshell.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Entidade de transferência de Fornecedor")
public @Data
class FornecedorDTO implements Serializable {

    @ApiModelProperty(value = "CPF/CNPJ do Fornecedor")
    private String cpfCnpj;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @ApiModelProperty(value = "Data da última atualização do cadastro do Usuário")
    private LocalDate dataAtualizado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @ApiModelProperty(value = "Data do cadastro do Usuário")
    private LocalDate dataCadastrado;

    @ApiModelProperty(value = "Razao Social do Fornecedor")
    private String razaoSocial;

    @ApiModelProperty(value = "Nome Fantasia do Fornecedor")
    private String nomeFantasia;

    @ApiModelProperty(value = "nome do Tipo de Pessoa do Fornecedor")
    private String tipoPessoa;

    @ApiModelProperty(value = "Indica se o Fornecedor está ativo ou não")
    private Boolean situacao;

}
