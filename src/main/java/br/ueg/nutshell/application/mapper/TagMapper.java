package br.ueg.nutshell.application.mapper;


import br.ueg.nutshell.application.dto.TagDTO;
import br.ueg.nutshell.application.model.Tag;
import org.mapstruct.Mapper;

/**
 * Classe adapter referente a entidade {@link Tag}.
 *
 * @author João Augusto Moreira Ananias
 */
@Mapper(componentModel = "spring", uses = { StatusAtivoInativoMapper.class, SimNaoMapper.class, TagMapper.class})
public interface TagMapper {
    /**
     * Converte a entidade {@link Tag} em DTO {@link TagDTO}
     *
     * @param tag
     * @return
     */

    public TagDTO toDTO(Tag tag);

    /**
     * Converte o DTO {@link TagDTO} para entidade {@link Tag}
     *
     * @param tagDTO
     * @return
     */

   // @Mapping(target = "status", expression = "java( StatusAtivoInativo.getById( tagDTO.getIdStatus() ) )")
    public Tag toEntity(TagDTO tagDTO);
}
