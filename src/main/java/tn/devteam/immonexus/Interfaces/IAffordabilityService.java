package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Affordability;

import java.util.List;

public interface IAffordabilityService {
    void addAffordability(Affordability affordability);
    void updateAffordability(Affordability affordability);
    void deleteAffordability(Long id);
    List<Affordability> displayAffordabilities();
    double calculateAfforadability(Affordability affordability);
}
