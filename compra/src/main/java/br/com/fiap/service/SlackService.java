package br.com.fiap.service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SlackService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlackService.class);
    private static final String NEW_LINE = "\n";

    @Value("${slack.webhook}")
    private String urlSlackWebHook;

    public void sendMessageToSlack(String message){
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Mensagem Hortlife: " + message + NEW_LINE);
        process(messageBuilder.toString());
    }

    private void process(String message){
        Payload payload = Payload.builder()
                .channel("#lab-produtor")
                .username("Hortlife")
                .iconEmoji(":rocket:")
                .text(message)
                .build();

        try {
            WebhookResponse webhookResponse = Slack.getInstance().send(
                    urlSlackWebHook, payload);
            LOGGER.info("Code -> " + webhookResponse.getCode());
            LOGGER.info("Body -> " + webhookResponse.getBody());

        }catch (IOException e){
            LOGGER.error("Error WebhHook: " + urlSlackWebHook);
        }
    }
}
