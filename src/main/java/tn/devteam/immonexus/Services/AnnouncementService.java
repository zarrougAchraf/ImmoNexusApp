package tn.devteam.immonexus.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Interfaces.IAnnouncementService;
import tn.devteam.immonexus.Repository.AnnouncementRepository;

@Service
@Slf4j
public class AnnouncementService implements IAnnouncementService {
    @Autowired
    AnnouncementRepository announcementRepository;
}
