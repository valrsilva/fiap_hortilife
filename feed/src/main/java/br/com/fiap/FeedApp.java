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
			post.setDataCriacao(Calendar.getInstance().getTime());
			postRepository.save(post);
			
			Story story = new Story();
			story.setIdUsuario(1);
			story.setDescricao("Deliciosa melancia, muito soborosa!");
			story.setUrlImagem("https://d3o3bdzeq5san1.cloudfront.net/91/90922.jpg"); //https://i.giphy.com/media/WNP97pSLrjM4g/giphy.webp
			story.setDataCriacao(Calendar.getInstance().getTime());
			Calendar dtExpire = Calendar.getInstance();
			dtExpire.add(Calendar.HOUR_OF_DAY, 24);
			story.setDataExpiracao(dtExpire.getTime());
			storyRepository.save(story);
			
			Story story2 = new Story();
			story2.setIdUsuario(1);
			story2.setDescricao("Deliciosa melancia, muito soborosa!");
			story2.setUrlImagem("https://d3o3bdzeq5san1.cloudfront.net/91/90922.jpg"); //https://i.giphy.com/media/WNP97pSLrjM4g/giphy.webp
			story2.setDataCriacao(Calendar.getInstance().getTime());
			Calendar dtExpire2 = Calendar.getInstance();
			dtExpire2.add(Calendar.HOUR_OF_DAY, 24);
			story2.setDataExpiracao(dtExpire2.getTime());
			storyRepository.save(story2);
			
			Story story3 = new Story();
			story3.setIdUsuario(1);
			story3.setDescricao("Deliciosa melancia, muito soborosa!");
			story3.setUrlImagem("https://d3o3bdzeq5san1.cloudfront.net/91/90922.jpg"); //https://i.giphy.com/media/WNP97pSLrjM4g/giphy.webp
			story3.setDataCriacao(Calendar.getInstance().getTime());
			Calendar dtExpire3 = Calendar.getInstance();
			dtExpire3.add(Calendar.HOUR_OF_DAY, 24);
			story3.setDataExpiracao(dtExpire3.getTime());
			storyRepository.save(story3);
			
			Story story4 = new Story();
			story4.setIdUsuario(1);
			story4.setDescricao("Deliciosa melancia, muito soborosa!");
			story4.setUrlImagem("https://d3o3bdzeq5san1.cloudfront.net/91/90922.jpg"); //https://i.giphy.com/media/WNP97pSLrjM4g/giphy.webp
			story4.setDataCriacao(Calendar.getInstance().getTime());
			Calendar dtExpire4 = Calendar.getInstance();
			dtExpire4.add(Calendar.HOUR_OF_DAY, 24);
			story4.setDataExpiracao(dtExpire4.getTime());
			storyRepository.save(story4);
			
			Story story5 = new Story();
			story5.setIdUsuario(1);
			story5.setDescricao("Deliciosa melancia, muito soborosa!");
			story5.setUrlImagem("https://d3o3bdzeq5san1.cloudfront.net/91/90922.jpg"); //https://i.giphy.com/media/WNP97pSLrjM4g/giphy.webp
			story5.setDataCriacao(Calendar.getInstance().getTime());
			Calendar dtExpire5 = Calendar.getInstance();
			dtExpire5.add(Calendar.HOUR_OF_DAY, 24);
			story5.setDataExpiracao(dtExpire5.getTime());
			storyRepository.save(story5);
			
			Story story6 = new Story();
			story6.setIdUsuario(1);
			story6.setDescricao("Deliciosa melancia, muito soborosa!");
			story6.setUrlImagem("https://d3o3bdzeq5san1.cloudfront.net/91/90922.jpg"); //https://i.giphy.com/media/WNP97pSLrjM4g/giphy.webp
			story6.setDataCriacao(Calendar.getInstance().getTime());
			Calendar dtExpire6 = Calendar.getInstance();
			dtExpire6.add(Calendar.HOUR_OF_DAY, 24);
			story6.setDataExpiracao(dtExpire6.getTime());
			storyRepository.save(story6);
			
			Agrupamento agrupamento = new Agrupamento();
			agrupamento.setDescricao("Destaques");
			agrupamento.setPosts(new ArrayList<>());
			agrupamento.getPosts().add(post);
			agrupamentoRepository.save(agrupamento);
			
			Agrupamento agrupamento2 = new Agrupamento();
			agrupamento2.setDescricao("Mais relevantes");
			agrupamento2.setPosts(new ArrayList<>());
			agrupamento2.getPosts().add(post);
			agrupamentoRepository.save(agrupamento2);
			
			Agrupamento agrupamento3 = new Agrupamento();
			agrupamento3.setDescricao("Mais vendidos");
			agrupamento3.setPosts(new ArrayList<>());
			agrupamento3.getPosts().add(post);
			agrupamentoRepository.save(agrupamento3);
			
			postRepository.findAll().forEach(System.out::println);
			storyRepository.findAll().forEach(System.out::println);
			agrupamentoRepository.findAll().forEach(System.out::println);

		}

	}

}
