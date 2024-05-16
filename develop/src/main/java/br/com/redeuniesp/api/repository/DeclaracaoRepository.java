package br.com.redeuniesp.api.repository;

import br.com.redeuniesp.api.model.Auth;
import br.com.redeuniesp.api.model.Declaracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclaracaoRepository extends JpaRepository<Declaracao, Long> {
    ;
}
