package br.com.fiap.database;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "cartao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cartao {
    @NonNull
    @Column(name = "id")
    private long id;
    @NonNull
    @Column(name = "nome")
    private String nome;
    @NonNull
    @Column(name = "numero_cartao")
    private String numeroCartao;
    @NonNull
    @Column(name = "validade")
    private Date validade;
    @NonNull
    @Column(name = "codigo_seguranca")
    private int codigoSeguranca;
    @NonNull
    @Column(name = "tipo")
    private String tipo;
}
