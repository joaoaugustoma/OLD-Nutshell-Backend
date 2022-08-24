package br.ueg.nutshell.application.mapper;


import br.ueg.nutshell.application.enums.StatusSimNao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class SimNaoMapper {
    public StatusSimNao asStatusSimNao(boolean valor){
        return StatusSimNao.getByIdStatusSimNao(valor);
    }

    public boolean asBoolean(StatusSimNao valor){
        if(valor == null){
            return false;
        }
        return valor.toString().equalsIgnoreCase(StatusSimNao.SIM.toString());
    }
}
