package br.com.fiap.database;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cartao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartao")
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
    @ManyToOne
    @JoinColumn(name = "id")
    private Consumidor consumidor;
}
