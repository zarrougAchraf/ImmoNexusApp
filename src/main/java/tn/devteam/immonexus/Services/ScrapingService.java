package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Interfaces.IScrapingService;
import tn.devteam.immonexus.Repository.ScrapingRepository;

@Service
@Slf4j
public class ScrapingService implements IScrapingService {
    @Autowired
    ScrapingRepository scrapingRepository;
}
