package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.MortgageParameters;

import java.util.List;

public interface IMortgageParametersService {
    void addMortgageParameters(MortgageParameters mortgageParameters);
    void updateMortgageParameters(MortgageParameters mortgageParameters);
    void deleteMortgageParameters(Long id);
    List<MortgageParameters> displayMortgageParameterss();
}
