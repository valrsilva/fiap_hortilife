package br.com.fiap.database;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

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
    
    @Column(name = "foto", length = 32000)
    private String foto;
    
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioSeguido> usuariosSeguidos;
    
    
}