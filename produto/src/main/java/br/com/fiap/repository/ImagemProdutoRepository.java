package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.ImagemProduto;

@Repository
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long>{
	
}
