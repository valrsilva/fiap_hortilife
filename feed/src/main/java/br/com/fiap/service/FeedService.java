package br.com.fiap.service;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
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
import com.google.gson.reflect.TypeToken;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.com.fiap.dto.Produto;
import br.com.fiap.dto.Usuario;

@Service
public class FeedService {

	@Autowired
    DiscoveryClient discoveryClient;
	
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
	
	
	@HystrixCommand(fallbackMethod = "produtoFallback", commandProperties=
		{@HystrixProperty(
		name="execution.isolation.thread.timeoutInMilliseconds",value="12000")})
	public Produto getProduto(long idProduto) throws Exception {
		
		Produto produto = null;
		
		List<ServiceInstance> list = discoveryClient.getInstances("produto");
		ServiceInstance service2 = list.get(0); URI micro2URI = service2.getUri();
		  
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		  
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			ResponseEntity<String> forEntity = restTemplate.getForEntity(micro2URI +
					"/produtos/" + idProduto, String.class);
			
			if(forEntity.getStatusCode().is2xxSuccessful()) {
				Gson gson = new Gson();
				produto = gson.fromJson(forEntity.getBody(), Produto.class);
			}
			
		} catch (HttpClientErrorException ex)   {
			
		    if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
		    	//exists = false;
		    }
		    
		}
		
		return produto;
		
	}
	
	@SuppressWarnings("unused")
	private Produto produtoFallback(long idProduto) throws Exception{
		return new Produto();
	}
	
	
	@HystrixCommand(fallbackMethod = "getUsuariosSeguidosFallback", commandProperties=
		{@HystrixProperty(
		name="execution.isolation.thread.timeoutInMilliseconds",value="12000")})
	public List<Usuario> getUsuariosSeguidos(long idUsuario) throws Exception {
		
		List<Usuario> usuario = null;
		
		List<ServiceInstance> list = discoveryClient.getInstances("usuario");
		ServiceInstance service2 = list.get(0); URI micro2URI = service2.getUri();
		  
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		  
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			ResponseEntity<String> forEntity = restTemplate.getForEntity(micro2URI +
					"/usuariosSeguidos/" + idUsuario, String.class);
			
			if(forEntity.getStatusCode().is2xxSuccessful()) {
				Gson gson = new Gson();
				Type listType = new TypeToken<ArrayList<Usuario>>(){}.getType();
				usuario = gson.fromJson(forEntity.getBody(), listType);
			}
			
		} catch (HttpClientErrorException ex)   {
			
		    if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
		    	//exists = false;
		    }
		    
		}
		
		return usuario;
		
	}
	
	@SuppressWarnings("unused")
	private List<Usuario> getUsuariosSeguidosFallback(long idUsuario) throws Exception{
		return new ArrayList<>();
	}
	
	
}
