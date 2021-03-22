package br.com.fiap.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.fiap.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicListener {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Value("${usuario.topic}")
    private String topicName;


    @KafkaListener(topics = "${usuario.topic}", groupId = "group_id")
    public void listener(String payload){
//
//    	System.out.println("Tópico: " + topicName);
//        System.out.println("Payload consumido: " + payload);
//
//        slackServer.sendMessageToSlack(payload);
//
//        Notificacao notificacao = new Notificacao();
//        notificacao.setMessage(payload);
//        notificacao.setData(new Date());
//
//        notificacaoRepository.save(notificacao);
//
    }

}
