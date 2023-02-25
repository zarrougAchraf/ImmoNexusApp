package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Advertising;
import tn.devteam.immonexus.Entities.PopulationCible;
import tn.devteam.immonexus.Interfaces.IPopulationCibleService;
import tn.devteam.immonexus.Repository.AdevertisingRepository;
import tn.devteam.immonexus.Repository.PopulationCibleRepository;

import java.util.List;
@Service
@Slf4j
public class PopulationCibleService implements IPopulationCibleService {
    @Autowired
    PopulationCibleRepository populationCibleRepository;
    @Autowired
    AdevertisingRepository adevertisingRepository;


    @Override
    public PopulationCible addPopulationCible(PopulationCible populationCible){


        return populationCibleRepository.save(populationCible) ;
    }
    @Override
    public List<PopulationCible> getAllPopulationCible()
    {

        return populationCibleRepository.findAll();
    }
    @Override
    public PopulationCible getPopulationCibleById(Long idPop)
    {

        return populationCibleRepository.findById(idPop).orElse(null);
    }

    @Override
    public PopulationCible updatePopulationCible(PopulationCible populationCible){

        return populationCibleRepository.save(populationCible);
    }

    @Override
    public void deleteById(Long idPopulationCible){

        populationCibleRepository.deleteById(idPopulationCible);
    }

    @Override
    public void deleteAll(){

        populationCibleRepository.deleteAll();
    }

    @Override
    public void affectPopulationCibleToAdvertising(Long idPopulationCible,Long idAd){
        PopulationCible populationCible=populationCibleRepository.findById(idPopulationCible).orElse(null);
        Advertising ad=adevertisingRepository.findById(idAd).orElse(null);

        populationCible.setAdvertising(ad);
     //   ad.setSponsor(sponsor);
        populationCibleRepository.save(populationCible);
    }



}
