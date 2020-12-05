package br.com.fiap.kafka;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.fiap.model.Notificacao;
import br.com.fiap.repository.NotificacaoRepository;
import br.com.fiap.service.SlackService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicListener {

    @Autowired
    private SlackService slackServer;

    @Autowired
    private NotificacaoRepository notificacaoRepository;
    
    @Value("${notificacao.topic}")
    private String topicName;


    @KafkaListener(topics = "${notificacao.topic}", groupId = "group_id")
    public void listener(String payload){
    	
    	System.out.println("Tópico: " + topicName);
        System.out.println("Payload consumido: " + payload);
        
        slackServer.sendMessageToSlack(payload);
        
        Notificacao notificacao = new Notificacao();
        notificacao.setMessage(payload);
        notificacao.setData(new Date());
        
        notificacaoRepository.save(notificacao);
        
    }

}
