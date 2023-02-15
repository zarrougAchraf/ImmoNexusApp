package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Interfaces.IRatingService;
import tn.devteam.immonexus.Repository.RatingRepository;

@Service
@Slf4j
public class RatingService implements IRatingService {
    @Autowired
    RatingRepository ratingRepository;
}
