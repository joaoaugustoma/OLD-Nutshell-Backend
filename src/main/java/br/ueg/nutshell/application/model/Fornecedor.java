package br.ueg.nutshell.application.model;

import br.ueg.nutshell.application.configuration.Constante;
import br.ueg.nutshell.application.enums.StatusAtivoInativo;
import br.ueg.nutshell.application.enums.converter.StatusSimNaoConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TBL_FORNECEDOR", schema = Constante.DATABASE_OWNER)
@EqualsAndHashCode()
@SequenceGenerator(name = "TBL_S_FORNECEDOR", sequenceName = "TBL_S_FORNECEDOR", allocationSize = 1, schema = Constante.DATABASE_OWNER)
public @Data
class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_S_FORNECEDOR")
    @Column(name = "ID_FORNECEDOR", nullable = false)
    private Long id;

    @Column(name = "DATA_CADASTRADO", nullable = false)
    private LocalDate dataCadastrado;

    @Column(name = "DATA_ATUALIZADO", nullable = false)
    private LocalDate dataAtualizado;

    @Column(name = "RAZAO_SOCIAL_FORNECEDOR", length = 100, nullable = false)
    private String razaoSocial;

    @Column(name = "NOME_FANTASIA_FORNECEDOR", length = 100)
    private String nomeFantasia;

    @Column(name = "CPF_CNPJ_FORNECEDOR", length = 100, nullable = false)
    private String cpfCnpj;

    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "ID_TIPO_FORNECEDOR", referencedColumnName = "ID_TIPO_FORNECEDOR", nullable = false)
    private TipoPessoa tipoPessoa;

    @Convert(converter = StatusSimNaoConverter.class)
    @Column(name = "E_FORNECEDOR", length = 1, nullable = false)
    private StatusAtivoInativo situacao;

}
