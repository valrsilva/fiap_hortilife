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

import br.com.fiap.model.Compra;
import br.com.fiap.repository.CompraRepository;
import br.com.fiap.service.CompraService;

@RestController
@CrossOrigin
@RefreshScope
public class CompraController {

	@Autowired
	CompraRepository compraRepository;
	
	@Autowired
	CompraService compraService;

	@Autowired
	DiscoveryClient discoveryClient;

	@Value("${max-results:10}")
    private int maxResults;
	
	@GetMapping("/compra")
	public Iterable<Compra> listAll() {
		
		return compraRepository.findAll(PageRequest.of(0, maxResults, Sort.by(new Order[0])));
		
	}

	@GetMapping("/compra/{id}")
	public ResponseEntity<Compra> findOne(@PathVariable("id") long idMidia) {
		
		Optional<Compra> midiaOpt = compraRepository.findById(idMidia);
		
		if(midiaOpt.isPresent()) {
			return new ResponseEntity<Compra>(midiaOpt.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Compra>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/compra")
	public ResponseEntity<?> saveOne(@RequestBody Compra midia) {
		Compra savedMidia = compraService.gravarNovaMidia(midia);
		return new ResponseEntity<Compra>(savedMidia, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/compra/{id}")
	public ResponseEntity<String> deleteOne(@PathVariable("id") long idMidia) {
		
		Optional<Compra> midiaOpt = compraRepository.findById(idMidia);
		
		if(midiaOpt.isPresent()) {
			compraService.removerMidia(idMidia);
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
	}



}