package br.com.fiap;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;

import br.com.fiap.model.Categoria;
import br.com.fiap.model.Produto;
import br.com.fiap.repository.CategoriaRepository;
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
		CategoriaRepository categoriaRepository;

		@Autowired
		ProdutoService compraService;
		
		@Override
		public void run(ApplicationArguments arg0) throws Exception {

			Categoria verdurasETemperos = categoriaRepository.save(new Categoria("Verduras e Temperos"));
			Categoria legumes = categoriaRepository.save(new Categoria("Legumes"));
			Categoria frutas = categoriaRepository.save(new Categoria("Frutas"));
			
			categoriaRepository.findAll().forEach(System.out::println);
			
			Produto prod1 = new Produto();
			prod1.setNome("Espinafre Orgânico");
			prod1.setValor(5.80);
			prod1.setDetalhes("Esse espinafre é muito bom!");
			prod1.setCategoria(verdurasETemperos);
			prod1.setVolume("100-200 gramas");
			prod1.setAtivo(true);
			prod1.setEstoque(20);
			prod1.setDataInclusao(Calendar.getInstance().getTime());

			produtoRepository.save(prod1);
			
			produtoRepository.findAll().forEach(System.out::println);

		}

	}

}
