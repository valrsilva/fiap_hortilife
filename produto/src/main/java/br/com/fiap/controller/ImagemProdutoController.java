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

import br.com.fiap.model.ImagemProduto;
import br.com.fiap.repository.ImagemProdutoRepository;

@RestController
@CrossOrigin
@RefreshScope
public class ImagemProdutoController {

	@Autowired
	ImagemProdutoRepository imagemProdutoRepository;
	
	@Value("${max-results:10}")
    private int maxResults;
	
	@GetMapping("/imagem-produto")
	public List<ImagemProduto> listAll() {
		return imagemProdutoRepository.findAll(PageRequest.of(0, maxResults, Sort.by(new Order[0]))).getContent();
	}
	
	@PostMapping("/imagem-produto")
	public ResponseEntity<?> saveOne(@RequestBody ImagemProduto imagemProduto) {
		ImagemProduto savedProduto = imagemProdutoRepository.save(imagemProduto);
		return new ResponseEntity<ImagemProduto>(savedProduto, HttpStatus.OK);
	}
	
	@DeleteMapping("/imagem-produto/{id}")
	public ResponseEntity<String> deleteOne(@PathVariable("id") long id) {
		
		Optional<ImagemProduto> findOpt = imagemProdutoRepository.findById(id);
		
		if(findOpt.isPresent()) {
			imagemProdutoRepository.deleteById(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
	}

}