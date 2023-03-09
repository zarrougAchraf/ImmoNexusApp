package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Mortgage;

import java.util.List;

public interface IMortgageService {
    String addMortgage(Mortgage Mortgage);
    void updateMortgage(Mortgage Mortgage);
    void deleteMortgage(Long id);
    List<Mortgage> displayMortgages();
    public String calculateMaxMortgageAmount(Mortgage mortgage);
    Double getAvreageLoanAmountByType(String loanType);
}
