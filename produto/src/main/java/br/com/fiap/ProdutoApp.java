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
			prod1.setDetalhes("Essa banana é muito boa!");
			prod1.setCategoria(frutas);
			prod1.setSubcategoria(bananas);
			prod1.setVolume("100-200 gramas");
			prod1.setAtivo(true);
			prod1.setEstoque(20);
			prod1.setDataInclusao(Calendar.getInstance().getTime());
			prod1.setImagemPrincipal("https://carrefourbr.vtexassets.com/arquivos/ids/197486/59870_2.jpg?v=637272440099630000");
			prod1.setIdUsuario(1);
			produtoRepository.save(prod1);
			
			Produto prod2 = new Produto();
			prod2.setNome("Abacate Manteiga");
			prod2.setValor(5.80);
			prod2.setDetalhes("Esse abacate é muito bom!");
			prod2.setCategoria(frutas);
			prod2.setSubcategoria(abacates);
			prod2.setVolume("100-200 gramas");
			prod2.setAtivo(true);
			prod2.setEstoque(20);
			prod2.setDataInclusao(Calendar.getInstance().getTime());
			prod2.setImagemPrincipal("https://cd.shoppub.com.br/cenourao/media/cache/30/e1/30e12fb9f905711a1cb4c18eec708334.jpg");
			prod2.setIdUsuario(3);
			produtoRepository.save(prod2);
			
			Produto prod3 = new Produto();
			prod3.setNome("Brócolis Ninja");
			prod3.setValor(5.80);
			prod3.setDetalhes("Esse brócolis é muito bom!");
			prod3.setCategoria(legumes);
			prod3.setSubcategoria(brocolis);
			prod3.setVolume("100-200 gramas");
			prod3.setAtivo(true);
			prod3.setEstoque(20);
			prod3.setDataInclusao(Calendar.getInstance().getTime());
			prod3.setImagemPrincipal("http://d3ugyf2ht6aenh.cloudfront.net/stores/174/441/products/ramoso1-efba3bd91e7d3ea78c15210387253162-640-0.jpg");
			prod3.setIdUsuario(1);
			produtoRepository.save(prod3);
			
			Produto prod4 = new Produto();
			prod4.setNome("Couve Manteiga");
			prod4.setValor(5.80);
			prod4.setDetalhes("Essa couve é muito boa!");
			prod4.setCategoria(legumes);
			prod4.setSubcategoria(couves);
			prod4.setVolume("100-200 gramas");
			prod4.setAtivo(true);
			prod4.setEstoque(20);
			prod4.setDataInclusao(Calendar.getInstance().getTime());
			prod4.setImagemPrincipal("https://octoshop.sfo2.digitaloceanspaces.com/lojas/padellasaobenedito/uploads_produto/couve-manteiga-5e975d60d4256.png");
			prod4.setIdUsuario(2);
			produtoRepository.save(prod4);
			
			produtoRepository.findAll().forEach(System.out::println);

		}

	}

}
