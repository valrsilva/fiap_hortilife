package br.com.fiap;

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
			
			produtoRepository.save(new Produto().comNome("Espinafre Orgânico").comValor(5.80).comDetalhes("Maço").comCategoria(verdurasETemperos));
			produtoRepository.save(new Produto().comNome("Couve Manteiga").comValor(5.80).comDetalhes("Maço").comCategoria(verdurasETemperos));
			produtoRepository.save(new Produto().comNome("Alho Poró Orgânico").comValor(11.90).comDetalhes("(250g - 350g)").comCategoria(verdurasETemperos));
			
			produtoRepository.save(new Produto().comNome("Tomate Italiano Orgânico").comValor(8.90).comDetalhes("(500g - 600g)").comCategoria(legumes));
			produtoRepository.save(new Produto().comNome("Cenoura Orgânica").comValor(6.90).comDetalhes("(500g - 600g)").comCategoria(legumes));
			
			produtoRepository.save(new Produto().comNome("Banana Prata Orgânica").comValor(8.80).comDetalhes("(600g - 800g)").comCategoria(frutas));
			produtoRepository.save(new Produto().comNome("Maçã Gala Orgânica").comValor(10).comDetalhes("(500g - 600g)").comCategoria(frutas));
			
			produtoRepository.findAll().forEach(System.out::println);

		}

	}

}
