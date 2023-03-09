package tn.devteam.immonexus.Interfaces;

import org.springframework.http.ResponseEntity;
import tn.devteam.immonexus.Entities.BotResponse;

public interface IChatBotService {
  ResponseEntity<BotResponse> askBot(String prompt);

}
