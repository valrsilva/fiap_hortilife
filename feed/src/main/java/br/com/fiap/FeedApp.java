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
			story.setUrlVideo("https://media3.giphy.com/media/WNP97pSLrjM4g/giphy.webp?cid=ecf05e47jbovc8adv3j1270t60mgq43nsa7pq0f8f3jpt3m2&rid=giphy.webp");
			story.setDataCriacao(Calendar.getInstance().getTime());
			Calendar dtExpire = Calendar.getInstance();
			dtExpire.add(Calendar.HOUR_OF_DAY, 24);
			story.setDataExpiracao(dtExpire.getTime());
			storyRepository.save(story);
			
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
