package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Interfaces.IClaimService;
import tn.devteam.immonexus.Repository.ClaimRepository;

import java.util.List;

@Service
@Slf4j
public class ClaimService implements IClaimService {
    @Autowired
    ClaimRepository claimRepository;

    @Override
    public List<Claim> retrieveAllClaims() {
        return (List<Claim>) claimRepository.findAll();
    }

    @Override
    public Claim addClaim(Claim e) {
        return claimRepository.save(e);
    }

    @Override
    public Claim updateClaim(Claim e) {
        return claimRepository.save(e);
    }

    @Override
    public Claim retrieveClaim(Long idClaim) {
        return claimRepository.findById(idClaim).get();
    }

    @Override
    public Claim removeClaim(Long idClaim) {
        claimRepository.deleteById(idClaim);

        return null;
    }
}
