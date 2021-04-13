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
public class UsuarioGenerico  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @NonNull
    @Column(name = "sobrenome")
    private String sobrenome;
    @NonNull
    @Column(name = "confirmarSenha")
    private String confirmarSenha;
    @NonNull
    @Column(name = "dataNascimento")
    private String dataNascimento;
    @NonNull
    @Column(name = "genero")
    private String genero;
    @NonNull
    @Column(name = "cpf")
    private String cpf;
}