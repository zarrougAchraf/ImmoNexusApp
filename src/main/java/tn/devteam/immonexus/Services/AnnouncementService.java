package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Announcement;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Interfaces.IAnnouncementService;
import tn.devteam.immonexus.Repository.AnnouncementRepository;
import tn.devteam.immonexus.Repository.UserRepository;

import java.util.List;

@Service
@Slf4j
public class AnnouncementService implements IAnnouncementService {
    @Autowired
    AnnouncementRepository announcementRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Announcement> retrieveAllAnnoucement() {
        List<Announcement> annonce = announcementRepository.findAll();
        return annonce;
    }

    @Override
    public Announcement addAnnouncement(Announcement a) {
        return announcementRepository.save(a);
    }

    @Override
    public Announcement updateAnnouncement(Announcement a) {
        return announcementRepository.save(a);
    }

    @Override
    public Announcement retrieveAnnouncement(Long idA) {
        return announcementRepository.findById(idA).orElse(null);
    }

    @Override
    public void removeAnnouncement(Long idA) {
        announcementRepository.deleteById(idA);
    }

    @Override
    public void removeAll() {
        announcementRepository.deleteAll();
    }
@Override
    public void affectAnnouncetoUser(Long idAnnonce, String firstName, String lastName) {
        Announcement announcement = announcementRepository.findById(idAnnonce).orElse(null);
        User user = userRepository.findByFirstnameLikeAndLastNameLike(firstName, lastName);
        announcement.setUser(user);
        announcementRepository.save(announcement);
    }
}
