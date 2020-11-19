package br.com.fiap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;

import br.com.fiap.repository.ItemRepository;
import br.com.fiap.service.ItemServiceImpl;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class BuscaApp {

    @Autowired
    private ItemServiceImpl itemService;
    
	public static void main(String[] args) {
		SpringApplication.run(BuscaApp.class, args);
	}
	
	@Component
	public class ApplicationRunnerBean implements ApplicationRunner {

		@Autowired
		ItemRepository itemRepository;

		@Override
		public void run(ApplicationArguments arg0) throws Exception {
	        itemService.loadSearch();
		}
	    
	}

}
