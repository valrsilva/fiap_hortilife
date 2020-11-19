package br.com.fiap.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.model.Produto;
import br.com.fiap.repository.ProdutoRepository;
import br.com.fiap.service.ProdutoService;

@RestController
@CrossOrigin
@RefreshScope
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ProdutoService produtoService;

	@Autowired
	DiscoveryClient discoveryClient;

	@Value("${max-results:10}")
    private int maxResults;
	
	@GetMapping("/produtos")
	public Iterable<Produto> listAll() {
		
		return produtoRepository.findAll(PageRequest.of(0, maxResults, Sort.by(new Order[0])));
		
	}

	@GetMapping("/produtos/{id}")
	public ResponseEntity<Produto> findOne(@PathVariable("id") long idProduto) {
		
		Optional<Produto> findOpt = produtoRepository.findById(idProduto);
		
		if(findOpt.isPresent()) {
			return new ResponseEntity<Produto>(findOpt.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/produtos")
	public ResponseEntity<?> saveOne(@RequestBody Produto produto) {
		Produto savedProduto = produtoService.gravarNovaMidia(produto);
		return new ResponseEntity<Produto>(savedProduto, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/produtos/{id}")
	public ResponseEntity<String> deleteOne(@PathVariable("id") long idMidia) {
		
		Optional<Produto> findOpt = produtoRepository.findById(idMidia);
		
		if(findOpt.isPresent()) {
			produtoService.removerMidia(idMidia);
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
	}

}