package br.ueg.nutshell.application.repository;

import br.ueg.nutshell.application.model.Amigo;
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
     * Retorna o total de Fornecedores encontrados na base de dados conforme o cpfCnpj
     * informado.
     *
     * @param cpfCnpj do fornecedor
     * @return
     */
    public Long countByCpfCnpj(final String cpfCnpj);

    /**
     * Listar todos os Fornecedores
     * @return
     */
    @Query("SELECT fornecedor from Fornecedor fornecedor ")
    public List<Fornecedor> findAll();

    /**
     * Retorna a instância do {@link Fornecedor} conforme o 'cpfCnpj' informado.
     *
     * @param cpfCnpj
     * @return
     */
    public Fornecedor findByCpfCnpj(final String cpfCnpj);

    /**
     * Retorna a instância do {@link Fornecedor} conforme o 'cpfCnpj' informado
     * e que não tenha o 'id' informado.
     *
     * @param cpfCnpj
     * @param id
     * @return
     */
    @Query(" SELECT fornecedor FROM Fornecedor fornecedor "
            + " WHERE fornecedor.cpfCnpj = :cpfCnpj "
            + " AND (:id IS NULL OR fornecedor.id != :id)")
    public Fornecedor findByCpfCnpjAndNotId(@Param("cpfCnpj") final String cpfCnpj, @Param("id") final Long id);

}
