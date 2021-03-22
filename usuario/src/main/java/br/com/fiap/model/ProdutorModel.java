package br.com.fiap.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ProdutorModel extends UsuarioGenericoModel{

    private float classificacaoProdutor;
    private String categoriaProduto;
    private String endereco;
}
