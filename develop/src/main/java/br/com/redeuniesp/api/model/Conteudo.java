package br.com.redeuniesp.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_conteudo")
public class Conteudo extends User{
    @Id
    private User user;
    private LocalDateTime dataDePublicacao;
    private String descricao;
//    private Nao sei o tipo de dado anexo;

}
