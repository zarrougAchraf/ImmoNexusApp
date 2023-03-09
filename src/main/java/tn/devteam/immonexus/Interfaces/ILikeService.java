package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Announcement;

import java.util.List;

public interface ILikeService {
    void LikeAnnounce(Long idAnnounce, Long idUser);

    Integer getLikes(Long idAnnonce);

    List<Announcement> recommanderAnnonces(Long userId);
}
