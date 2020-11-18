package br.com.fiap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.fiap.kafka.TopicProducer;
import br.com.fiap.model.Compra;
import br.com.fiap.repository.CompraRepository;

@Service
public class CompraService {
	
	@Autowired 
	TopicProducer producer;	
	
	@Autowired
    DiscoveryClient discoveryClient;
	
	@Autowired
	CompraRepository midiaRepository;
	
	public Compra gravarNovaMidia(Compra midia) {
		
		Compra savedMidia = midiaRepository.save(midia);
		
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
