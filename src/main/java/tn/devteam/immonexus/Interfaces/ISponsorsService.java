package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Sponsors;

import java.util.List;

public interface ISponsorsService {

    Sponsors addSponsor(Sponsors s);

    List<Sponsors> getAllSponsors();

    Sponsors getSponsorById(Long IdS);

    Sponsors updateSponsor(Sponsors s);

    void deleteById(Long idSponsor);

    void deleteAll();
}
