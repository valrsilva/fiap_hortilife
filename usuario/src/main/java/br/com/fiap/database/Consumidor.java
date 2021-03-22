package br.com.fiap.model;

import br.com.fiap.database.Cartao;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "consumidor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Consumidor{

    @NonNull
    @Column(name = "id")
    private long id;
    @NonNull
    @Column(name = "classificacao_consumidor")
    private float classificacaoConsumidor;
    @NonNull
    @Column(name = "endereco_entrega")
    private String enderecoEntrega;
    // TO-DO Many to One com a tabela de cartoes
//    @NonNull
//    @Column
//    private List<Cartao> cartoes;
}
