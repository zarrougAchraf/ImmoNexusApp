package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import tn.devteam.immonexus.Entities.Announcement;
import tn.devteam.immonexus.Entities.Likes;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Interfaces.IAnnouncementService;
import tn.devteam.immonexus.Interfaces.IImageVerificationService;
import tn.devteam.immonexus.Interfaces.ILikeService;
import tn.devteam.immonexus.Interfaces.IRatingService;
import tn.devteam.immonexus.Repository.AnnouncementRepository;
import tn.devteam.immonexus.Repository.LikeRepository;
import tn.devteam.immonexus.Repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Validated
public class AnnouncementService implements IAnnouncementService {
    @Autowired
    AnnouncementRepository announcementRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IImageVerificationService iImageVerificationService;
    @Autowired
    IRatingService ratingService;
    @Autowired
    ILikeService iLikeService;

    @Override
    public List<Announcement> retrieveAllAnnoucement() {
        List<Announcement> annonce = announcementRepository.findAll();
        return annonce;
    }

    @Override
    public void LikeAnnounce(Long idAnnounce, Long idUser) {
        iLikeService.LikeAnnounce(idAnnounce, idUser);
    }
    @Override
    public Integer getLikes(Long idAnnonce){
        Announcement   announcement=announcementRepository.findById(idAnnonce).orElse(null);
        return announcement.getLikesNumber();
    }

    @Override
    public List<Announcement> recommanderAnnonces(Long userId) {
        // Récupérer les annonces likées par l'utilisateur
        List<Long> annoncesLikes = likeRepository.findByUserId(userId).stream()
                .map(Likes::getAnnonceId)
                .collect(Collectors.toList());
        // Récupérer les annonces similaires aux annonces likées
        List<Announcement> annoncesRecommandees = announcementRepository.findByIdAnnounceIn(annoncesLikes);
        return annoncesRecommandees;
    }


    @Override
    public Announcement addAnnouncement(Announcement a/*, byte[] image*/) {
        //   a.setImage(image);
        //  Long idA= a.getIdAnnounce();

        // affectAnnouncetoUser(idA,);
        List<Announcement> announcementList = announcementRepository.findAll();

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
        User user = userRepository.findByFirstNameLikeAndLastNameLike(firstName, lastName);
        announcement.setUser(user);
        announcementRepository.save(announcement);
    }

    @Override
    public List<Announcement> classerAnnoncesParNoteMoyenne() {
        List<Announcement> annonces = announcementRepository.findAllByOrderByRateDesc();
        int rang = 1;
        for (Announcement annonce : annonces) {
            annonce.setRang(rang++);
        }
        announcementRepository.saveAll(annonces);
        return annonces;
    }

    @Override
    public float SetAVGRate(Long idAnnonce) {
        return ratingService.RateAVG(idAnnonce);
    }

}
