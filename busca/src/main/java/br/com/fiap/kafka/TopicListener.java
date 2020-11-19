package br.com.fiap.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.fiap.dto.ProdutoDto;
import br.com.fiap.model.Item;
import br.com.fiap.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicListener {
	
	@Autowired
	ItemRepository itemRepository;
	
    @KafkaListener(topics = "${produto.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String payload){
    	
        System.out.println("Payload consumido: " + payload);
        
        if(payload.startsWith("DELETE:")) {
        	
        	String idProduto = payload.split(":")[1];
        	Item findByCod = itemRepository.findByCodProduto(Long.parseLong(idProduto));
        	
            if(findByCod != null) {
            	itemRepository.deleteById(findByCod.getId());
            }
            
        }else {
        	
        	Gson gson = new Gson();
            ProdutoDto produtoDto = gson.fromJson(payload, ProdutoDto.class);
            
            Item itemByCod = itemRepository.findByCodProduto(produtoDto.getId());
            
            Item item = new Item();
            
            if(itemByCod != null) {
            	item.setId(itemByCod.getId());
            }
            
            item.setName(produtoDto.getTitulo());
            item.setDescription(produtoDto.getDescricao());
            item.setCodProduto(produtoDto.getId());
            
            itemRepository.save(item);
            
        }
        

    }
    
}