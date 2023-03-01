package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.ReclamationStatsDTO;
import tn.devteam.immonexus.Entities.ReclamationType;
import tn.devteam.immonexus.Entities.User;

import javax.transaction.Transactional;
import java.util.List;

public interface IClaimService {

  @Transactional
  Claim  addReclamation(Claim r, Long id);

  public List<Claim> getAllReclamations();
    public void deleteReclamation(Long id);
    public void marqueTraitee(Long id);
    public List<Claim> getReclamationsByUser(Long idUser);
    public List<Claim> getReclamationsNonTraitees();
    public List<Claim> getReclamationsTraitees();

  long getNumberOfClaims();

  List<ReclamationStatsDTO> getReclamationStatsByType();

  long countReclamationsByType(ReclamationType type);
}
