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
import br.com.fiap.model.Subcategoria;
import br.com.fiap.repository.CategoriaRepository;
import br.com.fiap.repository.ProdutoRepository;
import br.com.fiap.repository.SubcategoriaRepository;
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
		SubcategoriaRepository subcategoriaRepository;

		@Autowired
		ProdutoService compraService;
		
		@Override
		public void run(ApplicationArguments arg0) throws Exception {
			
			Categoria verdurasETemperos = categoriaRepository.save(new Categoria("Verduras e Temperos"));
			Categoria legumes = categoriaRepository.save(new Categoria("Legumes"));
			Categoria frutas = categoriaRepository.save(new Categoria("Frutas"));
			
			Subcategoria bananas = subcategoriaRepository.save(new Subcategoria("Bananas", frutas));
			Subcategoria macas = subcategoriaRepository.save(new Subcategoria("Legumes", frutas));
			Subcategoria abacates = subcategoriaRepository.save(new Subcategoria("Frutas", frutas));
			
			Subcategoria alfaces = subcategoriaRepository.save(new Subcategoria("Alfaces", verdurasETemperos));
			Subcategoria ruculas = subcategoriaRepository.save(new Subcategoria("Rúcula", verdurasETemperos));
			Subcategoria repolho = subcategoriaRepository.save(new Subcategoria("Repolho", verdurasETemperos));
			
			Subcategoria brocolis = subcategoriaRepository.save(new Subcategoria("Brócolis", legumes));
			Subcategoria couves = subcategoriaRepository.save(new Subcategoria("Couves", legumes));
			Subcategoria abobrinha = subcategoriaRepository.save(new Subcategoria("Abobrinha", legumes));
			
			categoriaRepository.findAll().forEach(System.out::println);
			subcategoriaRepository.findAll().forEach(System.out::println);
			
			Produto prod1 = new Produto();
			prod1.setNome("Banana nanica");
			prod1.setValor(5.80);
			prod1.setDetalhes("Essa banana é muito bom!");
			prod1.setCategoria(frutas);
			prod1.setSubcategoria(bananas);
			prod1.setVolume("100-200 gramas");
			prod1.setAtivo(true);
			prod1.setEstoque(20);
			prod1.setDataInclusao(Calendar.getInstance().getTime());
			prod1.setImagemPrincipal("https://cd.shoppub.com.br/cenourao/media/cache/30/e1/30e12fb9f905711a1cb4c18eec708334.jpg");
			produtoRepository.save(prod1);
			
			Produto prod2 = new Produto();
			prod2.setNome("Maça gala");
			prod2.setValor(5.80);
			prod2.setDetalhes("Essa maça é muito boa!");
			prod2.setCategoria(frutas);
			prod2.setSubcategoria(macas);
			prod2.setVolume("100-200 gramas");
			prod2.setAtivo(true);
			prod2.setEstoque(20);
			prod2.setDataInclusao(Calendar.getInstance().getTime());
			prod2.setImagemPrincipal("https://cd.shoppub.com.br/cenourao/media/cache/30/e1/30e12fb9f905711a1cb4c18eec708334.jpg");
			produtoRepository.save(prod2);
			
			Produto prod3 = new Produto();
			prod3.setNome("Abacate Manteiga");
			prod3.setValor(5.80);
			prod3.setDetalhes("Esse abacate é muito boa!");
			prod3.setCategoria(frutas);
			prod3.setSubcategoria(abacates);
			prod3.setVolume("100-200 gramas");
			prod3.setAtivo(true);
			prod3.setEstoque(20);
			prod3.setDataInclusao(Calendar.getInstance().getTime());
			prod3.setImagemPrincipal("https://cd.shoppub.com.br/cenourao/media/cache/30/e1/30e12fb9f905711a1cb4c18eec708334.jpg");
			produtoRepository.save(prod3);
			
			Produto prod4 = new Produto();
			prod4.setNome("Alface Americana");
			prod4.setValor(5.80);
			prod4.setDetalhes("Essa alface é muito boa!");
			prod4.setCategoria(verdurasETemperos);
			prod4.setSubcategoria(alfaces);
			prod4.setVolume("100-200 gramas");
			prod4.setAtivo(true);
			prod4.setEstoque(20);
			prod4.setDataInclusao(Calendar.getInstance().getTime());
			prod4.setImagemPrincipal("https://cd.shoppub.com.br/cenourao/media/cache/30/e1/30e12fb9f905711a1cb4c18eec708334.jpg");
			produtoRepository.save(prod4);
			
			Produto prod5 = new Produto();
			prod5.setNome("Rúcula Normal");
			prod5.setValor(5.80);
			prod5.setDetalhes("Essa rúcula é muito boa!");
			prod5.setCategoria(verdurasETemperos);
			prod5.setSubcategoria(ruculas);
			prod5.setVolume("100-200 gramas");
			prod5.setAtivo(true);
			prod5.setEstoque(20);
			prod5.setDataInclusao(Calendar.getInstance().getTime());
			prod5.setImagemPrincipal("https://cd.shoppub.com.br/cenourao/media/cache/30/e1/30e12fb9f905711a1cb4c18eec708334.jpg");
			produtoRepository.save(prod5);
			
			Produto prod6 = new Produto();
			prod6.setNome("Repolho Normal");
			prod6.setValor(5.80);
			prod6.setDetalhes("Esse repolho é muito bom!");
			prod6.setCategoria(verdurasETemperos);
			prod6.setSubcategoria(repolho);
			prod6.setVolume("100-200 gramas");
			prod6.setAtivo(true);
			prod6.setEstoque(20);
			prod6.setDataInclusao(Calendar.getInstance().getTime());
			prod6.setImagemPrincipal("https://cd.shoppub.com.br/cenourao/media/cache/30/e1/30e12fb9f905711a1cb4c18eec708334.jpg");
			produtoRepository.save(prod6);
			
			Produto prod7 = new Produto();
			prod7.setNome("Brócolis Ninja");
			prod7.setValor(5.80);
			prod7.setDetalhes("Esse brócolis é muito bom!");
			prod7.setCategoria(legumes);
			prod7.setSubcategoria(brocolis);
			prod7.setVolume("100-200 gramas");
			prod7.setAtivo(true);
			prod7.setEstoque(20);
			prod7.setDataInclusao(Calendar.getInstance().getTime());
			prod7.setImagemPrincipal("https://cd.shoppub.com.br/cenourao/media/cache/30/e1/30e12fb9f905711a1cb4c18eec708334.jpg");
			produtoRepository.save(prod7);
			
			Produto prod8 = new Produto();
			prod8.setNome("Couve Manteiga");
			prod8.setValor(5.80);
			prod8.setDetalhes("Essa couve é muito boa!");
			prod8.setCategoria(legumes);
			prod8.setSubcategoria(couves);
			prod8.setVolume("100-200 gramas");
			prod8.setAtivo(true);
			prod8.setEstoque(20);
			prod8.setDataInclusao(Calendar.getInstance().getTime());
			prod8.setImagemPrincipal("https://cd.shoppub.com.br/cenourao/media/cache/30/e1/30e12fb9f905711a1cb4c18eec708334.jpg");
			produtoRepository.save(prod8);
			
			Produto prod9 = new Produto();
			prod9.setNome("Abobrinha Italiana");
			prod9.setValor(5.80);
			prod9.setDetalhes("Essa abobrinha é muito boa!");
			prod9.setCategoria(legumes);
			prod9.setSubcategoria(abobrinha);
			prod9.setVolume("100-200 gramas");
			prod9.setAtivo(true);
			prod9.setEstoque(20);
			prod9.setDataInclusao(Calendar.getInstance().getTime());
			prod9.setImagemPrincipal("https://cd.shoppub.com.br/cenourao/media/cache/30/e1/30e12fb9f905711a1cb4c18eec708334.jpg");
			produtoRepository.save(prod9);
			
			produtoRepository.findAll().forEach(System.out::println);

		}

	}

}
