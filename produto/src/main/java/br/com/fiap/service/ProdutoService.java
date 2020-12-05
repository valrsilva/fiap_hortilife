package br.com.fiap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.fiap.kafka.TopicProducer;
import br.com.fiap.model.Produto;
import br.com.fiap.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired 
	TopicProducer producer;	
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public Produto save(Produto produto) {
		
		Produto savedOne = produtoRepository.save(produto);
		
		Gson gson = new Gson();
		String jsonMidia = gson.toJson(savedOne);
		producer.send(jsonMidia);
		
		return savedOne;
		
	}

}
