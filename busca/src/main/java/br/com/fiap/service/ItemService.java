package br.com.fiap.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.fiap.model.Item;

public interface ItemService {

	Item save(Item book);

    void delete(Item book);

    Item findOne(String id);

    Iterable<Item> findAll();

    Page<Item> findByName(String name, PageRequest pageRequest);

    Page<Item> findByDescription(String description, PageRequest pageRequest);
    
    void loadSearch();
	
}
