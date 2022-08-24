package br.ueg.nutshell.application.service;

import br.ueg.nutshell.application.exception.SistemaMessageCode;
import br.ueg.nutshell.comum.exception.BusinessException;
import br.ueg.nutshell.comum.util.CollectionUtil;
import br.ueg.nutshell.comum.util.ReflexaoModel;
import com.google.common.reflect.TypeToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericCrudService<MODEL, PRIMARYKEY_TYPE> {

    public abstract JpaRepository<MODEL,PRIMARYKEY_TYPE> getRepository();

    public abstract void validarCamposObrigatorios(MODEL model);
    public abstract void validarDuplicidade(MODEL model);
    protected abstract void inicializarModelParaInclusao(MODEL model);

    /**
     * Retorna uma lista de MODEL cadatrados .
     *
     * @return
     */
    public List<MODEL> getTodos() {
        List<MODEL> models = getRepository().findAll();

        if (CollectionUtil.isEmpty(models)) {
            throw new BusinessException(SistemaMessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO);
        }
        return models;
    }

    /**
     * Retorno um a MODEL pelo Id informado.
     * @param id - id to Model
     * @return
     */
    public MODEL getById(PRIMARYKEY_TYPE id){
        Optional<MODEL> modelOptional = getRepository().findById(id);

        if(!modelOptional.isPresent()){
            throw new BusinessException(SistemaMessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO);
        }
        return modelOptional.get();
    }


    /**
     * Salva a instânica de {MODEL na base de dados conforme os critérios
     * especificados na aplicação.
     *
     * @param model
     * @return
     */
    public MODEL salvar(MODEL model) {
        final TypeToken<PRIMARYKEY_TYPE> typeToken = new TypeToken<PRIMARYKEY_TYPE>(getClass()) { };

        if(ReflexaoModel.getIDValue(model, typeToken.getType()) == null){
            inicializarModelParaInclusao(model);
        }
        validarCamposObrigatorios(model);
        validarDuplicidade(model);

        MODEL modelSalved = getRepository().save(model);
        return modelSalved;
    }


    public MODEL remover(PRIMARYKEY_TYPE id){
        MODEL model = this.getById(id);
        getRepository().delete(model);
        return model;
    }
}
