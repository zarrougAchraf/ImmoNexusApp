package tn.devteam.immonexus.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.ReponseRec;
import tn.devteam.immonexus.Interfaces.IClaimService;
import tn.devteam.immonexus.Interfaces.IReponseRecService;
import tn.devteam.immonexus.Repository.ClaimRepository;
import tn.devteam.immonexus.Repository.ReponseRecRepository;

import java.util.List;

@Service
public class ResponseRecService implements IReponseRecService {

    @Autowired
    ReponseRecRepository reponseRecRepository;

    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    IClaimService iClaimService;


    @Override
    public String addReponse(ReponseRec reponse, Long id) {
        Claim rec = claimRepository.findById(id).get();
        reponse.setReclamation(rec);
        reponseRecRepository.save(reponse);
        rec.setReponseReclamation(reponse);
        iClaimService.marqueTraitee(id);
        claimRepository.save(rec);
        return "selket";
    }

    @Override
    public long getTempsAttenteReclamation(Long idReclamation) {
        Claim rec = (Claim) claimRepository.findById(idReclamation).orElse(null);

        long duree = Math.abs(rec.getReponseReclamation().getDateReponse().getTime()-rec.getDateReclamation().getTime());
        return duree;
    }
    @Override
    public long getTempsAttenteMoyenReclamation() {
        long total=0;
        List<Claim> myList = claimRepository.findAllTraitees();
        for(Claim rec : myList)
        {
            total+= Math.abs(rec.getReponseReclamation().getDateReponse().getTime()-rec.getDateReclamation().getTime());
        }
        return total/myList.size();

    }

    @Override
    public  List<String> suggestion( Long idRec) {
        Claim rec = claimRepository.findById(idRec).orElse(null);
        return reponseRecRepository.reponseSuggestion(rec.getType());
    }
}
