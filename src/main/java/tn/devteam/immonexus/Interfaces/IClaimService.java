package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Claim;

import java.util.List;

public interface IClaimService {
    List<Claim> retrieveAllClaims();

    Claim addClaim(Claim e);

    Claim updateClaim(Claim e);

    Claim retrieveClaim(Long idClaim);

    Claim removeClaim(Long idClaim);
}
