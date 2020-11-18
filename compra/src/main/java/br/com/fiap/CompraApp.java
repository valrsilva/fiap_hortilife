package br.com.fiap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;

import br.com.fiap.repository.CompraRepository;
import br.com.fiap.service.CompraService;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class CompraApp {

	public static void main(String[] args) {
		SpringApplication.run(CompraApp.class, args);
	}

	@Component
	public class ApplicationRunnerBean implements ApplicationRunner {

		@Autowired
		CompraRepository compraRepository;

		@Autowired
		CompraService compraService;
		
		@Override
		public void run(ApplicationArguments arg0) throws Exception {

			//midiaService.gravarNovaMidia(new Compra("Um Sonho de Liberdade (1994)", "Dois homens presos se reúnem ao longo de vários anos, encontrando consolo e eventual redenção através de atos de decência comum.","Drama","Filme",1994,"Frank Darabont"));
			//midiaRepository.findAll().forEach(System.out::println);

		}

	}

}
