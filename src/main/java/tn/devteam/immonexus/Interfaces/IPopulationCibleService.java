package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.PopulationCible;

import java.util.List;

public interface IPopulationCibleService {

    PopulationCible addPopulationCible(PopulationCible populationCible);

    List<PopulationCible> getAllPopulationCible();

    PopulationCible getPopulationCibleById(Long idPop);

    PopulationCible updatePopulationCible(PopulationCible populationCible);

    void deleteById(Long idPopulationCible);

    void deleteAll();

    void affectPopulationCibleToAdvertising(Long idPopulationCible, Long idAd);
}
