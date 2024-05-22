package br.com.redeuniesp.api.repository;

import br.com.redeuniesp.api.model.Conteudo;
import br.com.redeuniesp.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo, User> {
}
