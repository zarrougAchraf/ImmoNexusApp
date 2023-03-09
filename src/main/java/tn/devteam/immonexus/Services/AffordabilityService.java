package tn.devteam.immonexus.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Affordability;
import tn.devteam.immonexus.Entities.MortgageParameters;
import tn.devteam.immonexus.Interfaces.IAffordabilityService;
import tn.devteam.immonexus.Repository.AffordabilityRepository;
import tn.devteam.immonexus.Repository.MortgageParametersRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AffordabilityService implements IAffordabilityService {
    @Autowired
    AffordabilityRepository affordabilityRepository;
    @Autowired
    MortgageParametersRepository mortgageParametersRepository;
    @Override
    public void addAffordability(Affordability affordability) {
        affordabilityRepository.save(affordability);
    }

    @Override
    public void updateAffordability(Affordability affordability) {
        affordabilityRepository.save(affordability);
    }

    @Override
    public void deleteAffordability(Long id) {
        affordabilityRepository.deleteById(id);
    }

    @Override
    public List<Affordability> displayAffordabilities() {
        return affordabilityRepository.findAll();
    }
    public double calculateAfforadability(Affordability affordability){
        MortgageParameters mp=mortgageParametersRepository
                .findAll().stream()
                .sorted(Comparator.comparing(MortgageParameters::getIdMortgageParameters))
                .collect(Collectors.toList()).get(mortgageParametersRepository.findAll().size()-1);

        double monthlyInterestRate=mp.getInterestRate()/1200;
        System.out.println(monthlyInterestRate);
        double monthlyPropertyTax=(affordability.getLoanAmount()*(mp.getPropertyTaxRate()/1200));
        System.out.println(monthlyPropertyTax);
        double monthlyHomeOwnerInsurance=(affordability.getLoanAmount()*(affordability.getHomeownersInsuranceRate()/1200));
        System.out.println(monthlyHomeOwnerInsurance);
        double monthlyLoanPayment=(monthlyInterestRate* affordability.getLoanAmount())/(1-Math.pow(1+monthlyInterestRate,-affordability.getLoanTerme()*12));
        System.out.println(monthlyLoanPayment);
        double monthlyPropertyPayment=monthlyLoanPayment+monthlyPropertyTax+monthlyHomeOwnerInsurance;
        System.out.println(monthlyPropertyPayment);
        double totalmonthlyDebtPayment= affordability.getMonthlyDebtPayments()+monthlyPropertyPayment;
        System.out.println(totalmonthlyDebtPayment);
        double totalMonthlyExpenses=affordability.getOtherMonthlyExpenses()+monthlyPropertyPayment;
        System.out.println(totalMonthlyExpenses);
        double monthlyIncomeAfterExpenses= affordability.getMonthlyIncome()-totalMonthlyExpenses;
        System.out.println(monthlyIncomeAfterExpenses);
        double debToIncomeRation =totalMonthlyExpenses/affordability.getMonthlyIncome();
        System.out.println(debToIncomeRation);
        double maxDebtToIncomeRatio=0.43;
        if(debToIncomeRation>maxDebtToIncomeRatio){
            return 0;
        }
        else{
            return (1-debToIncomeRation)*100;
        }
    }
}
