package br.com.redeuniesp.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_declaracao")
@Data
@NoArgsConstructor
public class Declaracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String path;

    @Column
    private String fileName;

    public Declaracao(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    @OneToOne
    private User user;
}
