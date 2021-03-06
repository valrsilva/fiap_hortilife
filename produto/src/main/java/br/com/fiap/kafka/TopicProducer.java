package br.com.fiap.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
public class TopicProducer {
	
    @Value("${notificacao.topic}")
    private String topicName;


	private final KafkaTemplate<String, String> kafkatemplate;

    public TopicProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkatemplate = kafkaTemplate;
	}
    
    public void send(String message){
        System.out.println("Payload enviado: " + message);
        kafkatemplate.send(topicName, "Produto Inserido/Alterado :: " + message);
    }

}
