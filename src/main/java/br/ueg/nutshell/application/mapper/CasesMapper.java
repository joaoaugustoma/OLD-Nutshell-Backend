package br.ueg.nutshell.application.mapper;


import br.ueg.nutshell.application.dto.CasesDTO;
import br.ueg.nutshell.application.model.Cases;
import org.mapstruct.Mapper;

/**
 * Classe adapter referente a entidade {@link Cases}.
 *
 * @author UEG
 */
@Mapper(componentModel = "spring", uses = { SimNaoMapper.class})
public interface CasesMapper {
    /**
     * Converte a entidade {@link Cases} em DTO {@link CasesDTO}
     *
     * @param cases
     * @return
     */
    public CasesDTO toDTO(Cases cases);

    /**
     * Converte o DTO {@link Cases} para entidade {@link CasesDTO}
     *
     * @param casesDTO
     * @return
     */
    public Cases toEntity(CasesDTO casesDTO);
}
