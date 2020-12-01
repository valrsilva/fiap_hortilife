package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	public List<Produto> findAllProdutosByCategoriaId(long idCategoria);
	public List<Produto> findAllProdutosByNomeContainsOrDetalhesContains(String nome, String descricao);
	
}
