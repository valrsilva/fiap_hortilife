package br.com.fiap.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.com.fiap.model.Item;
import br.com.fiap.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
    DiscoveryClient discoveryClient;
	
    public Item save(Item book) {
        return itemRepository.save(book);
    }

    public void delete(Item book) {
    	itemRepository.delete(book);
    }

    public Item findOne(String id) {
        return itemRepository.findById(id).get();
    }

    public Iterable<Item> findAll() {
        return itemRepository.findAll();
    }

    public Page<Item> findByName(String name, PageRequest pageRequest) {
        return itemRepository.findByName(name, pageRequest);
    }

    public Page<Item> findByDescription(String description, PageRequest pageRequest) {
        return itemRepository.findByDescriptionContains(description, pageRequest);
    }
    
    @Override
    @HystrixCommand(fallbackMethod = "loadSearchFallback", commandProperties=
		{@HystrixProperty(
		name="execution.isolation.thread.timeoutInMilliseconds",value="12000")})
    public void loadSearch() {
    	
    	List<ServiceInstance> list = discoveryClient.getInstances("produto");
		ServiceInstance service2 = list.get(0); URI micro2URI = service2.getUri();
		  
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		  
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			ResponseEntity<String> forEntity = restTemplate.getForEntity(micro2URI +
					"/produtos", String.class);
			
			if(forEntity.getStatusCode().is2xxSuccessful()) {
				
			}
			
		} catch (HttpClientErrorException ex)   {
			
			ex.printStackTrace();
		    
		}
    	
    }
    
    @SuppressWarnings("unused")
	private boolean loadSearchFallback() throws Exception{
		throw new Exception("Serviço Produto indisponível.");
	}
	
}
