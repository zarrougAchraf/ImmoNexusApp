package tn.devteam.immonexus.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Interfaces.IClaimService;
import tn.devteam.immonexus.Repository.ClaimRepository;

@Service
@Slf4j
public class ClaimService implements IClaimService {
    @Autowired
    ClaimRepository claimRepository;
}
