package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Advertising;
import tn.devteam.immonexus.Entities.PopulationCible;

import java.time.LocalDate;
import java.util.List;

public interface IAdvertisingService {
    String nbrAdvertisingsBySponsor();

    Advertising addAdvertising(Advertising ad);

    List<Advertising> getAllAdvertising();

    Advertising getAdvertisingById(Long idAd);

    Advertising updateAdvertising(Advertising add);

    void deleteById(Long idAdvertising);

    void deleteAll();

    void affectAdvertisingToSponsor(Long idSponsor, Long idAd);


    public double testSimplex(Long id) ;
    public double tarifPubCaneaux(Long idPub);
    public double tarifPubParAge(Long idPop);
    public double tarifPubParGender(Long idPop);
    public double tarifPubParProfession(Long idPop);
    public String maxGain(Long id);





    List<Advertising> getAllActualAdvertising(LocalDate startDate, LocalDate endDate);
}
