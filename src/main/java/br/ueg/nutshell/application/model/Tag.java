package br.ueg.nutshell.application.model;

import br.ueg.nutshell.application.configuration.Constante;
import br.ueg.nutshell.application.enums.StatusAtivoInativo;
import br.ueg.nutshell.application.enums.StatusSimNao;
import br.ueg.nutshell.application.enums.converter.StatusAtivoInativoConverter;
import br.ueg.nutshell.application.enums.converter.StatusSimNaoConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TBL_TAG", schema = Constante.DATABASE_OWNER)
@EqualsAndHashCode
@SequenceGenerator(name = "TBL_S_TAG", sequenceName = "TBL_S_TAG", allocationSize = 1, schema = Constante.DATABASE_OWNER)
public @Data
class Tag implements Serializable {
    private static final long serialVersionUID = -8899975090870463525L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_S_GRUPO_USUARIO")
    @Column(name = "ID_TAG", nullable = false)
    private Long id;

    @Column(name = "NOME_TAG", length = 50)
    private String nome;

    @Column(name = "DESC_TAG", length = 200)
    private String descricao;

    @Convert(converter = StatusAtivoInativoConverter.class)
    @Column(name = "STATUS_TAG", nullable = false, length = 1)
    private StatusAtivoInativo status;
}
