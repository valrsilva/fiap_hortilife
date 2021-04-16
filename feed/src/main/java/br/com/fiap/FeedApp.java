package br.com.fiap;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;

import br.com.fiap.model.Agrupamento;
import br.com.fiap.model.AgrupamentoPost;
import br.com.fiap.model.Post;
import br.com.fiap.model.Story;
import br.com.fiap.repository.AgrupamentoRepository;
import br.com.fiap.repository.PostRepository;
import br.com.fiap.repository.StoryRepository;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class FeedApp {

	public static void main(String[] args) {
		SpringApplication.run(FeedApp.class, args);
	}

	@Component
	public class ApplicationRunnerBean implements ApplicationRunner {

		@Autowired
		PostRepository postRepository;
		
		@Autowired
		StoryRepository storyRepository;

		@Autowired
		AgrupamentoRepository agrupamentoRepository;
		
		@Override
		public void run(ApplicationArguments arg0) throws Exception {

			Post post = new Post();
			post.setIdUsuario(1);
			post.setDescricao("Delicioso abacate direto do pé.");
			post.setUrlImagem("https://cd.shoppub.com.br/cenourao/media/cache/30/e1/30e12fb9f905711a1cb4c18eec708334.jpg");
			post.setIdProduto(2);
			post.setDataCriacao(Calendar.getInstance().getTime());
			post = postRepository.save(post);
			
			Post post2 = new Post();
			post2.setIdUsuario(2);
			post2.setDescricao("Deliciosa banana direto do pé.");
			post2.setUrlImagem("https://carrefourbr.vtexassets.com/arquivos/ids/197486/59870_2.jpg?v=637272440099630000");
			post2.setIdProduto(1);
			post2.setDataCriacao(Calendar.getInstance().getTime());
			post2 = postRepository.save(post2);
			
			Post post3 = new Post();
			post3.setIdUsuario(3);
			post3.setDescricao("Delicioso brócolis direto do pé.");
			post3.setUrlImagem("http://d3ugyf2ht6aenh.cloudfront.net/stores/174/441/products/ramoso1-efba3bd91e7d3ea78c15210387253162-640-0.jpg");
			post3.setIdProduto(3);
			post3.setDataCriacao(Calendar.getInstance().getTime());
			post3 = postRepository.save(post3);
			
			Post post4 = new Post();
			post4.setIdUsuario(1);
			post4.setDescricao("Deliciosa couve direto do pé.");
			post4.setUrlImagem("https://octoshop.sfo2.digitaloceanspaces.com/lojas/padellasaobenedito/uploads_produto/couve-manteiga-5e975d60d4256.png");
			post4.setIdProduto(4);
			post4.setDataCriacao(Calendar.getInstance().getTime());
			post4 = postRepository.save(post4);
			
			
			Story story = new Story();
			story.setIdUsuario(1);
			story.setDescricao("Story de Deliciosa banana, muito soborosa!");
			story.setUrlImagem("https://i.pinimg.com/736x/d8/69/16/d86916839652d45172f46906ac79c619.jpg"); //https://i.giphy.com/media/WNP97pSLrjM4g/giphy.webp
			story.setDataCriacao(Calendar.getInstance().getTime());
			Calendar dtExpire = Calendar.getInstance();
			dtExpire.add(Calendar.HOUR_OF_DAY, 24);
			story.setDataExpiracao(dtExpire.getTime());
			storyRepository.save(story);
			
			Story story2 = new Story();
			story2.setIdUsuario(2);
			story2.setDescricao("Story de Deliciosa melancia, muito soborosa!");
			story2.setUrlImagem("https://lh3.googleusercontent.com/proxy/oH3qG44AH5nyRisBiZh9q5pAmWg-kHrPW_N1CfJrIxXznJbILq2M--77CF4dsl5q5re4CsujyM7nqIbrc-hDQHU66LqYfDocoGTbHAEcXF0o4Lq8tDjs"); //https://i.giphy.com/media/WNP97pSLrjM4g/giphy.webp
			story2.setDataCriacao(Calendar.getInstance().getTime());
			Calendar dtExpire2 = Calendar.getInstance();
			dtExpire2.add(Calendar.HOUR_OF_DAY, 24);
			story2.setDataExpiracao(dtExpire2.getTime());
			storyRepository.save(story2);
			
			Story story3 = new Story();
			story3.setIdUsuario(3);
			story3.setDescricao("Story de Deliciosa maça, muito soborosa!");
			story3.setUrlImagem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTan9UHSpuM8VoVp9Zp4GrRc69AS3NkUwJkBQ&usqp=CAU"); //https://i.giphy.com/media/WNP97pSLrjM4g/giphy.webp
			story3.setDataCriacao(Calendar.getInstance().getTime());
			Calendar dtExpire3 = Calendar.getInstance();
			dtExpire3.add(Calendar.HOUR_OF_DAY, 24);
			story3.setDataExpiracao(dtExpire3.getTime());
			storyRepository.save(story3);
			
			
			Agrupamento agrupamento = new Agrupamento();
			agrupamento.setDescricao("Destaques");
			agrupamento.setPosts(new ArrayList<>());
			agrupamento.getPosts().add(new AgrupamentoPost(0, post, agrupamento));
			agrupamento.getPosts().add(new AgrupamentoPost(0, post2, agrupamento));
			agrupamento.getPosts().add(new AgrupamentoPost(0, post3, agrupamento));
			agrupamento.getPosts().add(new AgrupamentoPost(0, post4, agrupamento));
			agrupamentoRepository.save(agrupamento);
			
			Agrupamento agrupamento2 = new Agrupamento();
			agrupamento2.setDescricao("Mais relevantes");
			agrupamento2.setPosts(new ArrayList<>());
			agrupamento2.getPosts().add(new AgrupamentoPost(0, post, agrupamento2));
			agrupamento2.getPosts().add(new AgrupamentoPost(0, post2, agrupamento2));
			agrupamento2.getPosts().add(new AgrupamentoPost(0, post3, agrupamento2));
			agrupamento2.getPosts().add(new AgrupamentoPost(0, post4, agrupamento2));
			agrupamentoRepository.save(agrupamento2);
			
			Agrupamento agrupamento3 = new Agrupamento();
			agrupamento3.setDescricao("Mais vendidos");
			agrupamento3.setPosts(new ArrayList<>());
			agrupamento3.getPosts().add(new AgrupamentoPost(0, post, agrupamento3));
			agrupamento3.getPosts().add(new AgrupamentoPost(0, post2, agrupamento3));
			agrupamento3.getPosts().add(new AgrupamentoPost(0, post3, agrupamento3));
			agrupamento3.getPosts().add(new AgrupamentoPost(0, post4, agrupamento3));
			agrupamentoRepository.save(agrupamento3);
			
			
			postRepository.findAll().forEach(System.out::println);
			storyRepository.findAll().forEach(System.out::println);
			agrupamentoRepository.findAll().forEach(System.out::println);

		}

	}

}
