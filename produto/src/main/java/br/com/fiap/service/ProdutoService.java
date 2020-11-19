package br.com.fiap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
    DiscoveryClient discoveryClient;
	
	@Autowired
	ProdutoRepository midiaRepository;
	
	public Produto gravarNovaMidia(Produto midia) {
		
		Produto savedMidia = midiaRepository.save(midia);
		
		Gson gson = new Gson();
		String jsonMidia = gson.toJson(savedMidia);
		producer.send(jsonMidia);
		
		return savedMidia;
		
	}
	
	public void removerMidia(long idMidia) {
		
		midiaRepository.deleteById(idMidia);
		producer.send("DELETE:" + idMidia);
		
	}

}
