package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
}
