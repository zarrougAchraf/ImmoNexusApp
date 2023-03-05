package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.*;
import tn.devteam.immonexus.Interfaces.IAdvertisingService;
import tn.devteam.immonexus.Repository.AdevertisingRepository;
import tn.devteam.immonexus.Repository.SponsorsRepository;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
public class AdvertisingService implements IAdvertisingService {
    @Autowired
    AdevertisingRepository adevertisingRepository;
    @Autowired
    SponsorsRepository sponsorsRepository;








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
        return("le sponsor qui a plusieurs publicites "+sponsors.getName()+"a comme nbr"+ n) ;
    }


    @Override
    public Advertising addAdvertising(Advertising ad){


        return adevertisingRepository.save(ad) ;
    }

    @Override
    public double calculerGainPublicitaire(Advertising advertising) {
        // Calculer le coût total des vues
        double coutTotalVues = advertising.getNbrVuesCible() * advertising.getCoutParVueCible();

        // Calculer le coût total des jours
        double coutTotalJours = advertising.getNbrJours() * advertising.getCoutParJour();

        double gainPublicitaire = coutTotalVues + coutTotalJours;

        return gainPublicitaire;
    }
    @Override
    public Long calculerNbreDesJours(Advertising advertising) {
         LocalDate startDate = advertising.getStartDate();
         LocalDate endDate = advertising.getEndDate();

        long nbrJours = ChronoUnit.DAYS.between(startDate, endDate);

        return nbrJours;

    }

    @Override
    public List<Advertising> getAllAdvertising(HttpServletRequest request)
    {
        List<Advertising> advertisings = adevertisingRepository.findAll();
        for (Advertising advertissing : advertisings) {
            incrementerNombreDeVue(advertissing.getIdAd(), request);
        }
        return advertisings;
    }

    @Override
    public List<Advertising> getAllActualAdvertising(LocalDate startDate, LocalDate endDate,HttpServletRequest request){

        List<Advertising> advertisings = adevertisingRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
        for (Advertising advertissing : advertisings) {
            incrementerNombreDeVue(advertissing.getIdAd(), request);
        }
            return advertisings;
    }
    @Override
    public void incrementerNombreDeVue(Long id, HttpServletRequest request) {
        Advertising advertising = adevertisingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Advertissing not found with id " + id));

        HttpSession session = request.getSession();
        String advertisingId = "annonce_" + id;

        if (session.getAttribute(advertisingId) == null) {
            advertising.setNbrVuesFinal(advertising.getNbrVuesFinal() + 1);
            session.setAttribute(advertisingId, true);
            adevertisingRepository.save(advertising);
        }
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









}
