package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Interfaces.ISponsorsService;
import tn.devteam.immonexus.Repository.SponsorsRepository;

@Service
@Slf4j
public class SponsorsService implements ISponsorsService {
    @Autowired
    SponsorsRepository sponsorsRepository;
}
