package br.com.fiap.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Table(name = "produtor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Produtor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NonNull
    @Column(name = "classificacao_produtor")
    private float classificacaoProdutor;
    @NonNull
    @Column(name = "categoria_produto")
    private String categoriaProduto;
    @NonNull
    @Column(name = "endereco")
    private String endereco;
}
