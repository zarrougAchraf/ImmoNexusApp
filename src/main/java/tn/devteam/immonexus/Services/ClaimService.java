package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.ReclamationStatsDTO;
import tn.devteam.immonexus.Entities.ReclamationType;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Interfaces.IClaimService;
import tn.devteam.immonexus.Repository.ClaimRepository;
import tn.devteam.immonexus.Repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClaimService implements IClaimService {

     private ClaimRepository claimRepository;
     private UserRepository userRepository;

     @Autowired
    public ClaimService(ClaimRepository claimRepository, UserRepository userRepository) {
        this.claimRepository = claimRepository;
        this.userRepository = userRepository;
    }


    private final String[] badWords = {"fuck", "shit", "connard"};

    public String filterComplaint(String complaint) {
        for (String badWord : badWords) {
            int wordStart = complaint.indexOf(badWord);
            while (wordStart >= 0) {
                int wordEnd = wordStart + badWord.length() - 1;
                String filteredWord = badWord.charAt(0) + repeat("*", badWord.length() - 2) + badWord.charAt(badWord.length() - 1);
                complaint = complaint.substring(0, wordStart) + filteredWord + complaint.substring(wordEnd + 1);
                wordStart = complaint.indexOf(badWord, wordEnd + 1);
            }
        }
        return complaint;
    }

    private String repeat(String s, int n) {
        return String.join("", Collections.nCopies(n, s));
    }

    @Override
    @Transactional
    public Claim  addReclamation(Claim r, Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
             }
            r.setUser(user);
        String filteredComplaint = filterComplaint(r.getDescription());
        r.setDescription(filteredComplaint);
            return claimRepository.save(r);

    }

    @Override
    public List<Claim> getAllReclamations() {
        return (List<Claim>) claimRepository.findAll();
    }

    @Override
    public void deleteReclamation(Long id) {
        Claim r= claimRepository.findById(id).get();
        claimRepository.delete(r);
    }

    @Override
    public void marqueTraitee(Long id) {
        Claim rec = (Claim) claimRepository.findById(id).orElse(null)	;
        rec.setTraitee(true);
        claimRepository.save(rec);
    }

    @Override
    public List<Claim> getReclamationsByUser(Long idUser) {
        List<Claim> first = (List<Claim>) claimRepository.findAll();
        List<Claim> second = new ArrayList<Claim>();
        for(Claim rec : first)
        {
            if(rec.getUser().getId()==idUser)
            {
                second.add(rec);

            }
        }

        return second;
    }

    @Override
    public List<Claim> getReclamationsNonTraitees() {
        List<Claim> myList = claimRepository.findAllNonTraitees();
        return myList;
    }

    @Override
    public List<Claim> getReclamationsTraitees() {
        List<Claim> myList = claimRepository.findAllTraitees();
        return myList;
    }


    //  @Override
    //public Claim addClaimToUser(Long userId, Claim claim) {
      //  User user = userRepository.getUserById(userId);
        //if (user == null) {
          //  throw new RuntimeException("User not found");
       // }
        //claim.setUser(user);
        //return claimRepository.save(claim);
    //}

// STATISTIQUE TEST
    @Override
    public long getNumberOfClaims() {
        return claimRepository.count();
    }

    @Override
    public List<ReclamationStatsDTO> getReclamationStatsByType() {
        List<Claim> reclamations = claimRepository.findAll();
        Map<ReclamationType, List<Claim>> reclamationsByType = reclamations.stream()
                .collect(Collectors.groupingBy(Claim::getType));

        List<ReclamationStatsDTO> statsList = new ArrayList<>();
        for (Map.Entry<ReclamationType, List<Claim>> entry : reclamationsByType.entrySet()) {
            ReclamationStatsDTO stats = new ReclamationStatsDTO();
            stats.setType(entry.getKey());
            stats.setCount(entry.getValue().size());
            stats.setPercentage((double) entry.getValue().size() / reclamations.size() * 100);
            statsList.add(stats);
        }

        return statsList;
    }

    @Override
    public long countReclamationsByType(ReclamationType type) {
        return claimRepository.countByType(type);
    }









}
