package br.ueg.nutshell.application.mapper;


import br.ueg.nutshell.application.dto.AmigoDTO;
import br.ueg.nutshell.application.dto.TipoAmigoDTO;
import br.ueg.nutshell.application.model.Amigo;
import br.ueg.nutshell.application.model.TipoAmigo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Classe adapter referente a entidade {@link Amigo}.
 *
 * @author UEG
 */
@Mapper(componentModel = "spring", uses = { SimNaoMapper.class})
public interface AmigoMapper {
    /**
     * Converte a entidade {@link Amigo} em DTO {@link AmigoDTO}
     *
     * @param amigo
     * @return
     */
    @Mapping(source = "tipo.id", target = "idTipoAmigo")
    @Mapping(source = "tipo.nome", target = "nomeTipoAmigo")
    public AmigoDTO toDTO(Amigo amigo);

    /**
     * Converte o DTO {@link TipoAmigoDTO} para entidade {@link TipoAmigo}
     *
     * @param amigoDTO
     * @return
     */
    @Mapping(source = "amigoDTO.idTipoAmigo", target = "tipo.id")
    public Amigo toEntity(AmigoDTO amigoDTO);
}
