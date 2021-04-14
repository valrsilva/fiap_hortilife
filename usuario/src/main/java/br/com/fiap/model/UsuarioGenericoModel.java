package br.com.fiap.model;


import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class UsuarioGenericoModel {

    private long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String login;
    private String senha;
    private String confirmarSenha;
    private String dataNascimento;
    private boolean ativo = true;
    private String genero;
    private String cpf;
}