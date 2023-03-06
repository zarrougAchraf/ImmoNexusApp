package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.UserAdress;
import tn.devteam.immonexus.Entities.Visit;

import java.util.List;

public interface IVisitService {

    public Visit createVisit(Visit visit, UserAdress userAdress);
    public List<Visit> getAllVisits();

    public Visit getVisitById(Long id);

    public Visit addVisit(Visit visit);

    public Visit updateVisit(Visit visit);

    public void deleteVisit(Long id);





    }
