package br.com.fiap.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.Item;

@Repository
public interface ItemRepository extends ElasticsearchRepository<Item, String> {

    Page<Item> findByName(String name, Pageable pageable);

    Page<Item> findByDescriptionContains(String description, Pageable pageable);
    
    Item findByCodProduto(long codProduto);

}
