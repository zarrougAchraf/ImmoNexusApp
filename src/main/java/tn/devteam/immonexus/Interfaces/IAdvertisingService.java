package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Advertising;
import tn.devteam.immonexus.Entities.PopulationCible;

import java.util.List;

public interface IAdvertisingService {
    Advertising addAdvertising(Advertising ad);

    List<Advertising> getAllAdvertising();

    Advertising getAdvertisingById(Long idAd);

    Advertising updateAdvertising(Advertising add);

    void deleteById(Long idAdvertising);

    void deleteAll();

    void affectAdvertisingToSponsor(Long idSponsor, Long idAd);


   // public String addPublicite(Advertising p);
   // public List<Advertising> getAllPublicite();
   // public void deletePub(Long id);
    public double testSimplex(Long id) ;
    public double tarifPubCaneaux(Long idPub);
    public double tarifPubParAge(Long idPop);
    public double tarifPubParGender(Long idPop);
    public double tarifPubParProfession(Long idPop);
    public String maxGain(Long id);




}
