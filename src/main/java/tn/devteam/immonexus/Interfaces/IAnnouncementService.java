package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Announcement;

import java.util.List;

public interface IAnnouncementService {
    List<Announcement> retrieveAllAnnoucement();


    void LikeAnnounce(Long idAnnounce, Long idUser);

    Integer getLikes(Long idAnnonce);

    List<Announcement> recommanderAnnonces(Long userId);

    Announcement addAnnouncement(Announcement a/*, byte[] image*/);

    Announcement updateAnnouncement(Announcement a);

    Announcement retrieveAnnouncement(Long idA);

    void removeAnnouncement(Long idA);

    void removeAll();

    void affectAnnouncetoUser(Long idAnnonce, String firstName, String lastName);

    List<Announcement> classerAnnoncesParNoteMoyenne();

    float SetAVGRate(Long idAnnonce);

}
