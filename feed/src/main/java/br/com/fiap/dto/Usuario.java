package br.com.fiap.dto;


import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Usuario {

    private long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private boolean ativo;
    private String foto;
    
}