package br.com.fiap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.model.Item;
import br.com.fiap.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepository;

    public Item save(Item book) {
        return itemRepository.save(book);
    }

    public void delete(Item book) {
    	itemRepository.delete(book);
    }

    public Item findOne(String id) {
        return itemRepository.findById(id).get();
    }

    public Iterable<Item> findAll() {
        return itemRepository.findAll();
    }

    public Page<Item> findByName(String name, PageRequest pageRequest) {
        return itemRepository.findByName(name, pageRequest);
    }

    public Page<Item> findByDescription(String description, PageRequest pageRequest) {
        return itemRepository.findByDescriptionContains(description, pageRequest);
    }
	
}
