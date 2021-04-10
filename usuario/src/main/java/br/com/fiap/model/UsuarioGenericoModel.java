package br.com.fiap.model;


import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class UsuarioGenericoModel {

    private long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private boolean ativo;
}