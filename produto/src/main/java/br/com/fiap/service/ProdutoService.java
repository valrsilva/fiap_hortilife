package br.com.fiap.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.com.fiap.kafka.TopicProducer;
import br.com.fiap.model.Produto;
import br.com.fiap.model.dto.Usuario;
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
	
	@HystrixCommand(fallbackMethod = "usuarioFallback", commandProperties=
		{@HystrixProperty(
		name="execution.isolation.thread.timeoutInMilliseconds",value="12000")})
	public Usuario getUsuario(long idUsuario) throws Exception {
		
		Usuario usuario = null;
		
		List<ServiceInstance> list = discoveryClient.getInstances("usuario");
		ServiceInstance service2 = list.get(0); URI micro2URI = service2.getUri();
		  
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		  
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			ResponseEntity<String> forEntity = restTemplate.getForEntity(micro2URI +
					"/buscaUsuario/" + idUsuario, String.class);
			
			if(forEntity.getStatusCode().is2xxSuccessful()) {
				Gson gson = new Gson();
				usuario = gson.fromJson(forEntity.getBody(), Usuario.class);
			}
			
		} catch (HttpClientErrorException ex)   {
			
		    if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
		    	//exists = false;
		    }
		    
		}
		
		return usuario;
		
	}
	
	@SuppressWarnings("unused")
	private Usuario usuarioFallback(long idUsuario) throws Exception{
		return new Usuario();
	}
	

}
