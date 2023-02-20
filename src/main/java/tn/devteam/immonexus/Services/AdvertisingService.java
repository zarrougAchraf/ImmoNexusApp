package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Advertising;
import tn.devteam.immonexus.Entities.Sponsors;
import tn.devteam.immonexus.Interfaces.IAdvertisingService;
import tn.devteam.immonexus.Repository.AdevertisingRepository;
import tn.devteam.immonexus.Repository.SponsorsRepository;

import java.util.List;

@Service
@Slf4j
public class AdvertisingService implements IAdvertisingService {
    @Autowired
    AdevertisingRepository adevertisingRepository;
    @Autowired
    SponsorsRepository sponsorsRepository;

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

}
