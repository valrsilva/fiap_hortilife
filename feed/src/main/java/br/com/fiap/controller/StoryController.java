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

import br.com.fiap.model.Story;
import br.com.fiap.repository.StoryRepository;

@RestController
@CrossOrigin
@RefreshScope
public class StoryController {

	@Autowired
	StoryRepository storyRepository;
	
	@Value("${max-results:10}")
    private int maxResults;
	
	@GetMapping("/story")
	public List<Story> listAll() {
		return storyRepository.findAll(PageRequest.of(0, maxResults, Sort.by(new Order[0]))).getContent();
	}
	
	@GetMapping("/story/{id}")
	public ResponseEntity<Story> findOne(@PathVariable("id") long idProduto) {
		Optional<Story> findOpt = storyRepository.findById(idProduto);
		if(findOpt.isPresent()) {
			return new ResponseEntity<Story>(findOpt.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Story>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/story")
	public ResponseEntity<?> saveOne(@RequestBody Story story) {
		Story savedProduto = storyRepository.save(story);
		return new ResponseEntity<Story>(savedProduto, HttpStatus.OK);
	}
	
	@DeleteMapping("/story/{id}")
	public ResponseEntity<String> deleteOne(@PathVariable("id") long id) {
		Optional<Story> findOpt = storyRepository.findById(id);
		if(findOpt.isPresent()) {
			storyRepository.deleteById(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}