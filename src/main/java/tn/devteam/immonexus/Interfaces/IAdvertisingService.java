package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Advertising;

import java.util.List;

public interface IAdvertisingService {
    Advertising addAdvertising(Advertising ad);

    List<Advertising> getAllAdvertising();

    Advertising getAdvertisingById(Long idAd);

    Advertising updateAdvertising(Advertising add);

    void deleteById(Long idAdvertising);

    void deleteAll();

    void affectAdvertisingToSponsor(Long idSponsor, Long idAd);
}
