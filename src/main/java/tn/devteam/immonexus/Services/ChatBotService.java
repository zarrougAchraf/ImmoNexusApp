package tn.devteam.immonexus.Services;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.BotResponse;
import tn.devteam.immonexus.Interfaces.IChatBotService;

@Service
@AllArgsConstructor
public class ChatBotService implements IChatBotService {
  private final ChatgptService chatgptService;

  @Override
  public ResponseEntity<BotResponse> askBot(String prompt) {
    String answer = this.chatgptService.sendMessage(prompt);

    return new ResponseEntity<>(new BotResponse(answer), HttpStatus.OK);
  }
}
