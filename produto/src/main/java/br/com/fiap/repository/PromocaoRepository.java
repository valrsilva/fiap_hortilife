package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.Promocao;

@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Long>{
	
}
