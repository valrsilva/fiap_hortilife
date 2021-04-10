package br.com.fiap.database;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Table(name = "usuario_generico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UsuarioGenerico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NonNull
    @Column(name = "nome")
    private String nome;
    @NonNull
    @Column(name = "email")
    private String email;
    @NonNull
    @Column(name = "login")
    private String login;
    @NonNull
    @Column(name = "senha")
    private String senha;
    @NonNull
    @Column(name = "ativo")
    private boolean ativo;
}