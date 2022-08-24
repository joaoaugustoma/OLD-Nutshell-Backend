package br.ueg.nutshell.application.repository;

import br.ueg.nutshell.application.model.Funcionalidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionalidadeRepository extends JpaRepository<Funcionalidade, Long> {
}
