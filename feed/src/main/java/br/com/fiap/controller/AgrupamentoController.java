package br.com.fiap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import br.com.fiap.dto.Produto;
import br.com.fiap.model.Agrupamento;
import br.com.fiap.model.AgrupamentoPost;
import br.com.fiap.repository.AgrupamentoRepository;
import br.com.fiap.service.FeedService;

@RestController
@CrossOrigin
@RefreshScope
public class AgrupamentoController {

	@Autowired
	AgrupamentoRepository agrupamentoRepository;
	
	@Autowired
	FeedService feedService;
	
	@Value("${max-results:10}")
    private int maxResults;
	
	@GetMapping("/agrupamento")
	public List<Agrupamento> listAll() {
		
		List<Agrupamento> content = agrupamentoRepository.findAll(PageRequest.of(0, maxResults, Sort.by(new Order[0]))).getContent();
		for(Agrupamento s : content) {
			for(AgrupamentoPost ap : s.getPosts()) {
				try {
					Produto produto = feedService.getProduto(ap.getPost().getIdProduto());
					ap.getPost().setProduto(produto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}
	
	@GetMapping("/agrupamento/{id}")
	public ResponseEntity<Agrupamento> findOne(@PathVariable("id") long idProduto) {
		Optional<Agrupamento> findOpt = agrupamentoRepository.findById(idProduto);
		if(findOpt.isPresent()) {
			return new ResponseEntity<Agrupamento>(findOpt.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Agrupamento>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/agrupamento")
	public ResponseEntity<?> saveOne(@RequestBody Agrupamento agrupamento) {
		Agrupamento savedProduto = agrupamentoRepository.save(agrupamento);
		return new ResponseEntity<Agrupamento>(savedProduto, HttpStatus.OK);
	}
	
	@DeleteMapping("/agrupamento/{id}")
	public ResponseEntity<String> deleteOne(@PathVariable("id") long id) {
		Optional<Agrupamento> findOpt = agrupamentoRepository.findById(id);
		if(findOpt.isPresent()) {
			agrupamentoRepository.deleteById(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}