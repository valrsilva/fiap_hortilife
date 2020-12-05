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

import br.com.fiap.model.Categoria;
import br.com.fiap.repository.CategoriaRepository;

@RestController
@CrossOrigin
@RefreshScope
public class CategoriaController {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Value("${max-results:10}")
    private int maxResults;
	
	@GetMapping("/categorias")
	public List<Categoria> listAll() {
		return categoriaRepository.findAll(PageRequest.of(0, maxResults, Sort.by(new Order[0]))).getContent();
	}
	
	@PostMapping("/categorias")
	public ResponseEntity<?> saveOne(@RequestBody Categoria categoria) {
		Categoria savedProduto = categoriaRepository.save(categoria);
		return new ResponseEntity<Categoria>(savedProduto, HttpStatus.OK);
	}
	
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<String> deleteOne(@PathVariable("id") long id) {
		
		Optional<Categoria> findOpt = categoriaRepository.findById(id);
		
		if(findOpt.isPresent()) {
			categoriaRepository.deleteById(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
	}

}