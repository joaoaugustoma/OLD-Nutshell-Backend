package br.ueg.nutshell.application.dto;

import br.ueg.nutshell.application.enums.StatusAtivoInativo;
import br.ueg.nutshell.application.model.IngredienteTag;
import br.ueg.nutshell.comum.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * Classe de transferência referente aos dados filtro para Ingrediente.
 *
 * @author UEG
 */
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Entidade de transferência de dados de filtro de Ingrediente")
public @Data class FiltroIngredienteDTO implements Serializable {

    private static final long serialVersionUID = 3180319002111253549L;

    @ApiModelProperty(value = "Nome do Ingrediente")
    private String nome;

    @ApiModelProperty(value = "Data de Entrada do Ingrediente")
    private LocalDate dataEntrada;

    @ApiModelProperty(value = "Status do Ingrediente")
    private String status;

    @ApiModelProperty(value = "Tags do Ingrediente")
    private Set<IngredienteTag> tags;

    /**
     * @return the id
     */
    @JsonIgnore
    public StatusAtivoInativo getStatusEnum() {
    	StatusAtivoInativo status = null;

        if (!Util.isEmpty(this.status)) {
            status = StatusAtivoInativo.getById(this.status);
        }
        return status;
    }

}
