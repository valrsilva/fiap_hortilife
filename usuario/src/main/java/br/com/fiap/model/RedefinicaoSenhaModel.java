package br.com.fiap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RedefinicaoSenhaModel {
    private String login;
    private String senha;
}
