package br.com.fiap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.model.Item;
import br.com.fiap.repository.ItemRepository;

@RestController
@CrossOrigin
public class BuscaController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ElasticsearchOperations elasticsearchTemplate;
	
	@GetMapping("/buscaPorNome")
	public Iterable<Item> buscaPorNome(@RequestParam String name) {
		return itemRepository.findByName(name, PageRequest.of(0, 10, Sort.by(new Order[0])));
	}
	
	@GetMapping("/buscaPorDescricao")
	public Iterable<Item> buscaPorDescricao(@RequestParam String description) {
		return itemRepository.findByDescriptionContains(description, PageRequest.of(0, 10, Sort.by(new Order[0])));
	}

}