package br.ueg.nutshell.application.mapper;


import br.ueg.nutshell.application.enums.StatusSimNao;
import br.ueg.nutshell.application.enums.TipoPessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class TipoPessoaMapper {

    public TipoPessoa asTipoPessoa(String nome){
        return TipoPessoa.getByIdNome(nome);
    }

    public String asString(TipoPessoa tipoPessoa){
        if(tipoPessoa == null){
            return null;
        }
        if(tipoPessoa.getId() == "F"){
            return TipoPessoa.PESSOA_FISICA.getId();
        } else{
            return TipoPessoa.PESSOA_JURIDICA.getId();
        }
    }
}
