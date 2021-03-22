package br.com.fiap.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class CartaoModel {
    private int id;
    private String nome;
    private String numeroCartao;
    private Date validade;
    private int codigoSeguranca;
    private String tipo;
}
