package br.com.fiap.controller;

import br.com.fiap.service.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/slack")
public class SlackController {
    @Autowired
    private SlackService slackServer;

    @PostMapping("/{message}")
    public ResponseEntity<String> sendMessageToSlack(@PathVariable(name = "message") String message){
        slackServer.sendMessageToSlack(message);
        return ResponseEntity.ok(message);
    }
}
