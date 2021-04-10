package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long>{
	
	public List<Story> findAllStoriesByIdUsuario(long idUsuario);
}
