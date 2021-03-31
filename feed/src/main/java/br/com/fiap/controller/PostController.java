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

import br.com.fiap.model.Post;
import br.com.fiap.repository.PostRepository;

@RestController
@CrossOrigin
@RefreshScope
public class PostController {

	@Autowired
	PostRepository postRepository;
	
	@Value("${max-results:10}")
    private int maxResults;
	
	@GetMapping("/post")
	public List<Post> listAll() {
		return postRepository.findAll(PageRequest.of(0, maxResults, Sort.by(new Order[0]))).getContent();
	}
	
	@GetMapping("/post/{id}")
	public ResponseEntity<Post> findOne(@PathVariable("id") long idProduto) {
		Optional<Post> findOpt = postRepository.findById(idProduto);
		if(findOpt.isPresent()) {
			return new ResponseEntity<Post>(findOpt.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/post")
	public ResponseEntity<?> saveOne(@RequestBody Post post) {
		Post savedProduto = postRepository.save(post);
		return new ResponseEntity<Post>(savedProduto, HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{id}")
	public ResponseEntity<String> deleteOne(@PathVariable("id") long id) {
		Optional<Post> findOpt = postRepository.findById(id);
		if(findOpt.isPresent()) {
			postRepository.deleteById(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}