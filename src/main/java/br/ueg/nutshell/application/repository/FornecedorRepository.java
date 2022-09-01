package br.ueg.nutshell.application.repository;

import br.ueg.nutshell.application.model.Fornecedor;
import br.ueg.nutshell.application.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe de persistência referente a entidade {@link Fornecedor}.
 *
 * @author João Augusto Moreira Ananias
 */
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>, FornecedorRepositoryCustom{

    /**
     * Retorna o número de {@link Fornecedor} pelo 'razaoSocial' , desconsiderando o
     * 'TipoFornecedor' com o 'id' informado.
     *
     * @param razaoSocial
     * @param idFornecedor
     * @return
     */
    @Query("SELECT COUNT(fornecedor) FROM Fornecedor fornecedor " +
            " WHERE lower(fornecedor.razaoSocial) LIKE lower(:razaoSocial)" +
            " AND(:idFornecedor IS NULL OR fornecedor.id != :idFornecedor)")
    public Long countByCpfCnpjlAndNotId(String cpfCpnj, Long idFornecedor);

    /**
     * Listar todos os Fornecedors
     * @return
     */
    @Query("SELECT fornecedor from Fornecedor fornecedor " +
            " INNER JOIN FETCH fornecedor.tipoPessoa tipoPessoa")
    public List<Fornecedor> findAll();

    /**
     * Busca uma {@link Fornecedor} pelo id Informado
     *
     * @param idFornecedor
     * @return
     */
    @Query("SELECT fornecedor from Fornecedor fornecedor " +
            " INNER JOIN FETCH fornecedor.tipoPessoa tipoPessoa " +
            " WHERE fornecedor.id = :idFornecedor ")
    public Optional<Fornecedor> findById( @Param("idFornecedor") final Long idFornecedor);

}
