package br.com.fiap.service;

import br.com.fiap.database.Consumidor;
import br.com.fiap.handler.BancoDadosException;
import br.com.fiap.model.ConsumidorModel;
import br.com.fiap.repository.ConsumidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarConsumidorService {

    @Autowired
    ConsumidorRepository consumidorRepository;

    public boolean execute(ConsumidorModel dadosConsumidor){
        cadastrarConsumidor(dadosConsumidor);
        return true;
    }

    private ConsumidorModel cadastrarConsumidor(ConsumidorModel dadosConsumidor) {
        try {
            consumidorRepository.save(converterParaEntity(dadosConsumidor));
        }catch (BancoDadosException ex){
            throw new BancoDadosException("Erro ao salvar dados do consumidor no banco de dados.", ex);
        }
        return dadosConsumidor;
    }

    private Consumidor converterParaEntity(ConsumidorModel dadosConsumidor){
        return Consumidor.builder()
                .id(dadosConsumidor.getId())
                .classificacaoConsumidor(dadosConsumidor.getClassificacaoConsumidor())
                .enderecoEntrega(dadosConsumidor.getEnderecoEntrega())
                .build();
    }
}
