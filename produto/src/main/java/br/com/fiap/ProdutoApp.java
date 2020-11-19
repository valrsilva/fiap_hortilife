package br.com.fiap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;

import br.com.fiap.model.Produto;
import br.com.fiap.repository.ProdutoRepository;
import br.com.fiap.service.ProdutoService;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class ProdutoApp {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoApp.class, args);
	}

	@Component
	public class ApplicationRunnerBean implements ApplicationRunner {

		@Autowired
		ProdutoRepository produtoRepository;

		@Autowired
		ProdutoService compraService;
		
		@Override
		public void run(ApplicationArguments arg0) throws Exception {

			produtoRepository.save(new Produto("Sabão em pó OMO", "1kg", 20.10));
			produtoRepository.findAll().forEach(System.out::println);

		}

	}

}
