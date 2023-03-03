package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.*;
import tn.devteam.immonexus.Entities.Simplex.*;
import tn.devteam.immonexus.Interfaces.IAdvertisingService;
import tn.devteam.immonexus.Repository.AdevertisingRepository;
import tn.devteam.immonexus.Repository.PopulationCibleRepository;
import tn.devteam.immonexus.Repository.SponsorsRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class AdvertisingService implements IAdvertisingService {
    @Autowired
    AdevertisingRepository adevertisingRepository;
    @Autowired
    SponsorsRepository sponsorsRepository;

    @Autowired
    PopulationCibleRepository popRepo;

    Simplex simplexx;

    @Override
public String nbrAdvertisingsBySponsor(){

      List<Sponsors> sponsorsList =sponsorsRepository.findAll();

        int n = 0;
        Sponsors sponsors = new Sponsors();

        for (Sponsors s : sponsorsList){
            for (Sponsors sp : sponsorsList) {
                   if (sp.getAdvertisingList().size()>s.getAdvertisingList().size()) {
                       n=sp.getAdvertisingList().size();
                       sponsors=sp;
                       log.info("fdfg"+n);
                   }
                }

        }
        return("le sponsor qui a plusieurs pub "+sponsors.getName()+"a comme nbr"+ n) ;
    }


    @Override
    public Advertising addAdvertising(Advertising ad){


        return adevertisingRepository.save(ad) ;
    }
    @Override
    public List<Advertising> getAllAdvertising()
    {

        return adevertisingRepository.findAll();
    }

    @Override
    public List<Advertising> getAllActualAdvertising(LocalDate startDate, LocalDate endDate){

        List<Advertising> advertisings = adevertisingRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);

            return advertisings;
    }
    @Override
    public Advertising getAdvertisingById(Long idAd)
    {

        return adevertisingRepository.findById(idAd).orElse(null);
    }

    @Override
    public Advertising updateAdvertising(Advertising add){

        return adevertisingRepository.save(add);
    }

    @Override
    public void deleteById(Long idAdvertising){
        adevertisingRepository.deleteById(idAdvertising);
    }

    @Override
    public void deleteAll(){

        adevertisingRepository.deleteAll();
    }
@Override
    public void affectAdvertisingToSponsor(Long idSponsor,Long idAd){
        Sponsors sponsor=sponsorsRepository.findById(idSponsor).orElse(null);
        Advertising ad=adevertisingRepository.findById(idAd).orElse(null);
        ad.setSponsor(sponsor);
        adevertisingRepository.save(ad);
    }

// ******************************



    // *****************************************************************************
    @Override
    public double testSimplex(Long id) {
        Advertising p = adevertisingRepository.findById(id).orElse(null);
       PopulationCible pc = p.getPopulationCible();
        Long idPop = p.getPopulationCible().getIdPop();

        double[] objectiveFunc = { p.getNbrVuesCible(),p.getNbrVuesFinal() };

// ****************

        double[][] constraintLeftSide = {

                { tarifPubParAge(idPop), tarifPubParGender(idPop) },
                { tarifPubParGender(idPop), tarifPubParProfession(idPop) },
                {tarifPubParAge(idPop),tarifPubParProfession(idPop)}

        };


// ****************
        Constraint[] constraintOperator = { Constraint.lessThan,
                Constraint.lessThan,
                Constraint.lessThan};

// ****************
        double[] constraintRightSide = { tarifPubCaneaux(id),
                tarifPubCaneaux(id),
                tarifPubCaneaux(id) };

// ****************

        Modeler model = new Modeler(constraintLeftSide, constraintRightSide, constraintOperator, objectiveFunc);

        Simplex simplex = new Simplex(model.getTableaux(),
                model.getNumberOfConstraint(),
                model.getNumberOfOriginalVariable(),
                simplexx.MAXIMIZE);
        // double[] x = simplex.primal();
        return  simplex.value();
    }

// *****************************************************************************

 /*   @Override
    public double tarifPubCaneaux(Advertising p) {
        if(p.getCanaux()== Canaux.FACEBOOK)
            return 10;
        if(p.getCanaux()==Canaux.INSTAGRAM)
            return 12;
        if(p.getCanaux()==Canaux.GOOGLE_ADS)
            return 15;
        else
            return 0;
    }*/
 @Override
 public double tarifPubCaneaux(Long idPub) {
     Advertising p = adevertisingRepository.findById(idPub).orElse(null);
     if(p.getCanaux()== Canaux.FACEBOOK)
         return 10;
     if(p.getCanaux()==Canaux.INSTAGRAM)
         return 12;
     if(p.getCanaux()==Canaux.GOOGLE_ADS)
         return 15;
     else
         return 0;
 }

// *****************************************************************************

    @Override
    public double tarifPubParAge(Long idPop) {
        PopulationCible pc = popRepo.findById(idPop).orElse(null);


        double coutAge;

        if (pc.getAge()>=18 && pc.getAge() < 26 )
            return coutAge = 4;
        if(  pc.getAge()>=26 && pc.getAge() < 45)
            return coutAge=6;
        if(pc.getAge()>=45)
            return coutAge=5;
        else
            return 0;
    }

    @Override
    public double tarifPubParGender(Long idPop) {
        PopulationCible pc = popRepo.findById(idPop).orElse(null);

        double coutGender;
        if(pc.getGender()==Gender.HOMME)
            return	coutGender=7;
        if(pc.getGender()==Gender.FEMME)
            return	coutGender=5;
        else
            return 1;
    }
    // *****************************************************************************
    @Override
    public double tarifPubParProfession(Long idPop) {

        PopulationCible pc = popRepo.findById(idPop).orElse(null);
        double coutP ;
        if(pc.getProfession()==Profession.MEDCIN)
            return	coutP=4;
        if(pc.getProfession()==Profession.LAWYYER)
            return coutP=3;
        else
            return 0;
    }
    // *****************************************************************************
    @Override
    public String maxGain(Long id) {
        Advertising p = adevertisingRepository.findById(id).orElse(null);
        double result =testSimplex(id);
        return "Le maximum du Gain de la publicité " +p.getNom() +" est: " +result;
    }






}
