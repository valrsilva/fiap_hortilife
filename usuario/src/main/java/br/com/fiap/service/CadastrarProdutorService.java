package br.com.fiap.service;

import br.com.fiap.handler.BancoDadosException;
import br.com.fiap.model.Produtor;
import br.com.fiap.model.ProdutorModel;
import br.com.fiap.repository.ProdutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarProdutorService {

    @Autowired
    ProdutorRepository produtorRepository;

    public boolean execute(ProdutorModel dadosProdutor){
        cadastrarProdutor(dadosProdutor);
        return true;
    }

    private void cadastrarProdutor(ProdutorModel dadosProdutor){
        try{
            produtorRepository.save(converterParaEntity(dadosProdutor));
        }catch (BancoDadosException ex){
            throw new BancoDadosException("Erro ao salvar dados do produtor no banco de dados.", ex);
        }
    }

    private Produtor converterParaEntity(ProdutorModel dadosProdutor){
        return Produtor.builder()
                .classificacaoProdutor(dadosProdutor.getClassificacaoProdutor())
                .categoriaProduto(dadosProdutor.getCategoriaProduto())
                .endereco(dadosProdutor.getEndereco())
                .build();
    }
}
