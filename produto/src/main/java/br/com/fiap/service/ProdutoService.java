package br.com.fiap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.com.fiap.kafka.TopicProducer;
import br.com.fiap.model.Produto;
import br.com.fiap.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired 
	TopicProducer producer;	
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
    DiscoveryClient discoveryClient;
	
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@HystrixCommand(fallbackMethod = "sendNotificacaoFallback", commandProperties=
		{@HystrixProperty(
		name="execution.isolation.thread.timeoutInMilliseconds",value="12000")})
	public void sendNotificacao(Produto produto) throws Exception {
		
		Gson gson = new Gson();
		String jsonMidia = gson.toJson(produto);
		producer.send(jsonMidia);
	}
	
	@SuppressWarnings("unused")
	private void sendNotificacaoFallback(Produto produto) throws Exception{
		throw new Exception("Serviço Notificacão indisponível.");
	}
	

}
