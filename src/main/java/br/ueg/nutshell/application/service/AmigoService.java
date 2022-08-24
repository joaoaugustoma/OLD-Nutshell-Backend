package br.ueg.nutshell.application.service;

import br.ueg.nutshell.application.dto.FiltroAmigoDTO;
import br.ueg.nutshell.application.enums.StatusSimNao;
import br.ueg.nutshell.application.exception.SistemaMessageCode;
import br.ueg.nutshell.application.model.Amigo;
import br.ueg.nutshell.application.repository.AmigoRepository;
import br.ueg.nutshell.comum.exception.BusinessException;
import br.ueg.nutshell.comum.util.CollectionUtil;
import br.ueg.nutshell.comum.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AmigoService extends GenericCrudService<Amigo, Long> {


    @Autowired
    private AmigoRepository amigoRepository;

    @Override
    public JpaRepository getRepository() {
        return amigoRepository;
    }

    /**
     * Retorna uma lista de {@link Amigo} conforme o filtro de pesquisa informado.
     *
     * @param filtroDTO
     * @return
     */
    public List<Amigo> getAmigosByFiltro(final FiltroAmigoDTO filtroDTO) {
        validarCamposObrigatoriosFiltro(filtroDTO);

        List<Amigo> amigos = amigoRepository.findAllByFiltro(filtroDTO);

        if (CollectionUtil.isEmpty(amigos)) {
            throw new BusinessException(SistemaMessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO_FILTROS);
        }

        return amigos;
    }

    /**
     * Verifica se pelo menos um campo de pesquisa foi informado, e se informado o
     * nome do Amigo
     *
     * @param filtroDTO
     */
    private void validarCamposObrigatoriosFiltro(final FiltroAmigoDTO filtroDTO) {
        boolean vazio = Boolean.TRUE;

        if (!Util.isEmpty(filtroDTO.getNome())) {
            vazio = Boolean.FALSE;
        }

        if (filtroDTO.getDataAmizade()!=null) {
            vazio = Boolean.FALSE;
        }

        if (filtroDTO.getIdTipoAmigo()!=null) {
            vazio = Boolean.FALSE;
        }

        if (filtroDTO.getAmigo()!=null) {
            vazio = Boolean.FALSE;
        }

        if (vazio) {
            throw new BusinessException(SistemaMessageCode.ERRO_FILTRO_INFORMAR_OUTRO);
        }
    }

    protected void inicializarModelParaInclusao(Amigo amigo){
        amigo.setAmigo(StatusSimNao.SIM);
    }



    /**
     * Verifica se os Campos Obrigatórios foram preenchidos.
     *
     * @param amigo
     */
    public void validarCamposObrigatorios(final Amigo amigo) {
        boolean vazio = Boolean.FALSE;

        if (Util.isEmpty(amigo.getNome())) {
            vazio = Boolean.TRUE;
        }

        if (amigo.getTipo()==null || amigo.getTipo().getId()== null) {
            vazio = Boolean.TRUE;
        }

        if (amigo.getAmigo()==null) {
            vazio = Boolean.TRUE;
        }

        if (vazio) {
            throw new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS);
        }
    }

    /**
     * Verifica se o TipoAmigo a ser salvo já existe na base de dados.
     *
     * @param amigo
     */
    public void validarDuplicidade(final Amigo amigo) {
        Long count = amigoRepository.countByNomeAndNotId(amigo.getNome(), amigo.getId());

        if (count > BigDecimal.ZERO.longValue()) {
            throw new BusinessException(SistemaMessageCode.ERRO_AMIGO_DUPLICADO);
        }
    }

}
