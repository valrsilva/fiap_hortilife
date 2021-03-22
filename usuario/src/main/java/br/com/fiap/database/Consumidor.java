package br.com.fiap.database;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "consumidor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Consumidor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NonNull
    @Column(name = "classificacao_consumidor")
    private float classificacaoConsumidor;
    @NonNull
    @Column(name = "endereco_entrega")
    private String enderecoEntrega;
    @OneToMany
    @JoinColumn(name = "id_cartao")
    private List<Cartao> cartoes;
}
