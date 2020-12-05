package br.com.fiap.controller;

import br.com.fiap.kafka.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final TopicProducer producer;

    @Autowired
    KafkaController(TopicProducer producer){
        this.producer = producer;
    }
    @PostMapping(value = "/send")
    public void sendMessageToKafkaTopic(@RequestBody String message){
        this.producer.send(message);
    }
}
