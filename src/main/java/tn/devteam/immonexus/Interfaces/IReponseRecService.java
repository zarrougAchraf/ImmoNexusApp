package tn.devteam.immonexus.Interfaces;

import org.springframework.http.ResponseEntity;
import tn.devteam.immonexus.Entities.ReponseRec;
import tn.devteam.immonexus.Services.ResponseRecService;

import java.util.List;

public interface IReponseRecService {

    String addReponse(ReponseRec reponse, Long id);


    long getTempsAttenteReclamation(Long idReclamation);

    long getTempsAttenteMoyenReclamation();

    List<String> suggestion(Long idRec);
}
