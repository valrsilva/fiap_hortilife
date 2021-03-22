package br.com.fiap.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ConsumidorModel extends UsuarioGenericoModel{

    private float classificacaoConsumidor;
    private String enderecoEntrega;
    private List<CartaoModel> cartoes;
}
