package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.PropertyValuation;

import java.util.List;

public interface IPropertyValuationService {
    void addPropertyValuation(PropertyValuation PropertyValuation);
    void updatePropertyValuation(PropertyValuation PropertyValuation);
    void deletePropertyValuation(Long id);
    List<PropertyValuation> displayPropertyValuations();
    public List<PropertyValuation> readPropertiesFromCSV() throws Exception;
    public double predictPrice(PropertyValuation userProperty) throws Exception;
    Long getPropertyValuationCountWithThreeOrMoreBedrooms();
}
