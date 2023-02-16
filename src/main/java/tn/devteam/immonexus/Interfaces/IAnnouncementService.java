package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Announcement;

import java.util.List;

public interface IAnnouncementService {
    List<Announcement> retrieveAllAnnoucement();


    Announcement addAnnouncement(Announcement a);

    Announcement updateAnnouncement(Announcement a);

    Announcement retrieveAnnouncement(Long idA);

    void removeAnnouncement(Long idA);

    void removeAll();

    void affectAnnouncetoUser(Long idAnnonce, String firstName, String lastName);
}
