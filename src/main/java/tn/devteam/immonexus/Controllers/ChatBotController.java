package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.devteam.immonexus.Entities.BotResponse;
import tn.devteam.immonexus.Interfaces.IChatBotService;


@RestController
@AllArgsConstructor
@RequestMapping("/ChatG")
public class ChatBotController {
    private final IChatBotService chatService;

    @GetMapping("/ask")
    public ResponseEntity<BotResponse> askBot(@RequestParam String prompt) {
        return this.chatService.askBot(prompt);
    }
}
