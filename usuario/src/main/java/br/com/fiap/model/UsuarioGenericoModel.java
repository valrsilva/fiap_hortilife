package br.com.fiap.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private char perfil;
    private boolean ativo;
    private Date dataInclusao;
    private Date dataAlteracao;
}