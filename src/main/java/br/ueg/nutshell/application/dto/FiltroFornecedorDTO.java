package br.ueg.nutshell.application.dto;

import br.ueg.nutshell.application.enums.StatusAtivoInativo;
import br.ueg.nutshell.application.enums.TipoPessoa;
import br.ueg.nutshell.comum.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Dados do filtro de pesquisa de Amigo")
public @Data class FiltroFornecedorDTO implements Serializable {
    @ApiModelProperty(value = "CPF/CNPJ do Fornecedor")
    private String cpfCnpj;

    @ApiModelProperty(value = "Razao Social do Fornecedor")
    private String razaoSocial;

    @ApiModelProperty(value = "nome do Tipo de Pessoa do Fornecedor")
    private String tipoPessoa;

    @ApiModelProperty(value = "Indica se o Fornecedor está ativo ou não")
    private String situacao;

    /**
     * @return the id
     */
    @JsonIgnore
    public StatusAtivoInativo getStatusEnum() {
        StatusAtivoInativo status = null;

        if (!Util.isEmpty(this.situacao)) {
            status = StatusAtivoInativo.getById(this.situacao);
        }
        return status;
    }

    /**
     * @return the id
     */
    @JsonIgnore
    public TipoPessoa getPessoaEnum() {
        TipoPessoa pessoaEnum = null;

        if (!Util.isEmpty(this.tipoPessoa)) {
            pessoaEnum = TipoPessoa.getById(this.tipoPessoa);
        }
        return pessoaEnum;
    }
}
