package br.com.fiap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.model.Notificacao;
import br.com.fiap.repository.NotificacaoRepository;

@RestController
@CrossOrigin
@RefreshScope
public class NotificacaoController {

	@Autowired
	NotificacaoRepository notificacaoRepository;
	
	@Autowired
	DiscoveryClient discoveryClient;

	@Value("${max-results:10}")
    private int maxResults;
	
	@GetMapping("/notificacoes")
	public Iterable<Notificacao> listAll() {
		return notificacaoRepository.findAll(PageRequest.of(0, maxResults, Sort.by(new Order[0])));
	}

}