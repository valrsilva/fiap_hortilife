package br.com.fiap.kafka;

import br.com.fiap.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class TopicListener {

    @Autowired
    private SlackService slackServer;

    @Value("${notification.topic}")
    private String topicName;


    @KafkaListener(topics = "${notification.topic}", groupId = "group_id")
    public void listener(String payload){
        slackServer.sendMessageToSlack(payload);
        System.out.println("Tópico: " + topicName);
        System.out.println("Payload consumido: " + payload);
    }

}
