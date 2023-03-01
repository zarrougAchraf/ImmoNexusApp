package tn.devteam.immonexus.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.ReponseRec;
import tn.devteam.immonexus.Interfaces.IReponseRecService;

import java.util.List;

@RestController
@RequestMapping("/ReponseRec")
public class ResponsRecController {

    @Autowired
    IReponseRecService iReponseRecService;

    @PostMapping("/add/{recId}")
    @ResponseBody
    public String addReponse(@RequestBody ReponseRec reponse, @PathVariable("recId") Long id)
    {
        return iReponseRecService.addReponse(reponse, id);
    }

    @GetMapping("/getTempsAttenteReclamation/{idReclamation}")
    public long getTempsAttenteReclamation(@PathVariable("idReclamation") Long idRec)
    {
        return iReponseRecService.getTempsAttenteReclamation(idRec);
    }

    @GetMapping("/getTempsAttenteMoyenReclamation")
    @ResponseBody
    public long getTempsAttenteMoyenReclamation()
    {
        return iReponseRecService.getTempsAttenteMoyenReclamation();
    }
    @GetMapping("/suggestions/{idRec}")
    public List<String> suggest(@PathVariable("idRec") Long id){
        return iReponseRecService.suggestion(id);
    }
}
