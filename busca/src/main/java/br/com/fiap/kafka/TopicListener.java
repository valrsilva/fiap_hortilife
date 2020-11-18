package br.com.fiap.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.fiap.dto.MidiaDto;
import br.com.fiap.model.Item;
import br.com.fiap.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicListener {
	
	@Autowired
	ItemRepository itemRepository;
	
    @KafkaListener(topics = "${catalogo.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String payload){
    	
        System.out.println("Payload consumido: " + payload);
        
        if(payload.startsWith("DELETE:")) {
        	
        	String idMidia = payload.split(":")[1];
        	Item findByCodMidia = itemRepository.findByCodMidia(Long.parseLong(idMidia));
        	
            if(findByCodMidia != null) {
            	itemRepository.deleteById(findByCodMidia.getId());
            }
            
        }else {
        	
        	Gson gson = new Gson();
            MidiaDto midiaDto = gson.fromJson(payload, MidiaDto.class);
            
            Item ItemByCodMidia = itemRepository.findByCodMidia(midiaDto.getId());
            
            Item item = new Item();
            
            if(ItemByCodMidia != null) {
            	item.setId(ItemByCodMidia.getId());
            }
            
            item.setName(midiaDto.getTitulo());
            item.setDescription(midiaDto.getDescricao());
            item.setCodMidia(midiaDto.getId());
            
            itemRepository.save(item);
            
        }
        

    }
    
}