package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Sponsors;
import tn.devteam.immonexus.Interfaces.ISponsorsService;
import tn.devteam.immonexus.Repository.SponsorsRepository;

import java.util.List;

@Service
@Slf4j
public class SponsorsService implements ISponsorsService {
    @Autowired
    SponsorsRepository sponsorsRepository;

    @Override
    public Sponsors addSponsor(Sponsors s){


        return sponsorsRepository.save(s) ;
    }
    @Override
    public List<Sponsors> getAllSponsors()
    {

        return sponsorsRepository.findAll();
    }
    @Override
    public Sponsors getSponsorById(Long IdS)
    {

        return sponsorsRepository.findById(IdS).orElse(null);
    }

    @Override
    public Sponsors updateSponsor(Sponsors s){

        return sponsorsRepository.save(s);
    }

    @Override
    public void deleteById(Long idSponsor){

        sponsorsRepository.deleteById(idSponsor);
    }

    @Override
    public void deleteAll(){

        sponsorsRepository.deleteAll();
    }


}
