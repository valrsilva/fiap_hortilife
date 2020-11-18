package br.com.fiap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;

import br.com.fiap.repository.ItemRepository;
import br.com.fiap.service.ItemServiceImpl;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class BuscaApp {

	@Autowired
    private ElasticsearchOperations es;

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
			
	        //itemService.save(new Item("Um Sonho de Liberdade (1994)", "Dois homens presos se reúnem ao longo de vários anos, encontrando consolo e eventual redenção através de atos de decência comum."));
	        //itemService.save(new Item("O Poderoso Chefão (1972)", "O patriarca idoso de uma dinastia do crime organizado transfere o controle de seu império clandestino para seu filho relutante."));
	        //itemService.save(new Item("O Poderoso Chefão II (1974)", "Em 1950, Michael Corleone, agora à frente da família, tenta expandir o negócio do crime a Las Vegas, Los Angeles e Cuba. Paralelamente, é revelada a história de Vito Corleone, e de como saiu da Sicília e chegou a Nova Iorque."));
	        //itemService.save(new Item("Planeta Terra II (2016)", "David Attenborough retorna neste documentário de tirar o fôlego que mostra a vida no planeta Terra."));
	        //itemService.save(new Item("Irmãos de Guerra", "A história da Easy Company da 101ª Divisão Aerotransportada do Exército dos EUA e sua missão na Segunda Guerra Mundial na Europa, desde a Operação Overlord até o Dia V-J."));
	        //itemService.save(new Item("Breaking Bad", "Um professor de química diagnosticado com câncer de pulmão se transforma em fabricante e vendedor de metanfetamina, a fim de garantir o futuro da sua família."));

		}
	    
	}

}
