package br.ueg.nutshell.application.service;

import br.ueg.nutshell.application.dto.FiltroTipoAmigoDTO;
import br.ueg.nutshell.application.exception.SistemaMessageCode;
import br.ueg.nutshell.application.model.TipoAmigo;
import br.ueg.nutshell.application.repository.TipoAmigoRepository;
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
public class TipoAmigoService extends GenericCrudService<TipoAmigo, Long> {

    @Autowired
    private TipoAmigoRepository tipoAmigoRepository;

    @Override
    public JpaRepository getRepository() {
        return tipoAmigoRepository;
    }

    /**
     * Retorna uma lista de {@link TipoAmigo} conforme o filtro de pesquisa informado.
     *
     * @param filtroDTO
     * @return
     */
    public List<TipoAmigo> getTipoAmigosByFiltro(final FiltroTipoAmigoDTO filtroDTO) {
        validarCamposObrigatoriosFiltro(filtroDTO);

        List<TipoAmigo> grupos = tipoAmigoRepository.findAllByFiltro(filtroDTO);

        if (CollectionUtil.isEmpty(grupos)) {
            throw new BusinessException(SistemaMessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO_FILTROS);
        }

        return grupos;
    }

    /**
     * Verifica se pelo menos um campo de pesquisa foi informado, e se informado o
     * nome do Grupo, verifica se tem pelo meno 4 caracteres.
     *
     * @param filtroDTO
     */
    private void validarCamposObrigatoriosFiltro(final FiltroTipoAmigoDTO filtroDTO) {
        boolean vazio = Boolean.TRUE;

        if (!Util.isEmpty(filtroDTO.getNome())) {
            vazio = Boolean.FALSE;
        }

        if (vazio) {
            throw new BusinessException(SistemaMessageCode.ERRO_FILTRO_INFORMAR_OUTRO);
        }
    }





    /**
     * Verifica se os Campos Obrigatórios foram preenchidos.
     *
     * @param tipoAmigo
     */
    public void validarCamposObrigatorios(final TipoAmigo tipoAmigo) {
        boolean vazio = Boolean.FALSE;

        if (Util.isEmpty(tipoAmigo.getNome())) {
            vazio = Boolean.TRUE;
        }

        if (vazio) {
            throw new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS);
        }
    }

    /**
     * Verifica se o TipoAmigo a ser salvo já existe na base de dados.
     *
     * @param tipoAmigo
     */
    public void validarDuplicidade(final TipoAmigo tipoAmigo) {
        Long count = tipoAmigoRepository.countByNomeAndNotId(tipoAmigo.getNome(), tipoAmigo.getId());

        if (count > BigDecimal.ZERO.longValue()) {
            throw new BusinessException(SistemaMessageCode.ERRO_TIPO_AMIGO_DUPLICADO);
        }
    }

    @Override
    protected void inicializarModelParaInclusao(TipoAmigo tipoAmigo) {
    }

}
