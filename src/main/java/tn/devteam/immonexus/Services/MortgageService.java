package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.BankParameters;
import tn.devteam.immonexus.Entities.Mortgage;
import tn.devteam.immonexus.Entities.MortgageParameters;
import tn.devteam.immonexus.Interfaces.IMortgageService;
import tn.devteam.immonexus.Repository.BankParametersRepository;
import tn.devteam.immonexus.Repository.MortgageParametersRepository;
import tn.devteam.immonexus.Repository.MortgageRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MortgageService implements IMortgageService {
    @Autowired
    MortgageRepository mortgageRepository;
    @Autowired
    MortgageParametersRepository mortgageParametersRepository;
    @Autowired
    BankParametersRepository bankParametersRepository;
    @Override
    public String addMortgage(Mortgage Mortgage) {
        if(controleDeSaisie(Mortgage).length()>0){
            return controleDeSaisie(Mortgage);
        }
        else{
            mortgageRepository.save(Mortgage);
            return "mortgage added successufly";
        }

    }

    @Override
    public void updateMortgage(Mortgage Mortgage) {
        mortgageRepository.save(Mortgage);
    }

    @Override
    public void deleteMortgage(Long id) {
        mortgageRepository.deleteById(id);
    }

    @Override
    public List<Mortgage> displayMortgages() {
        return mortgageRepository.findAll();
    }


    public String calculateMaxMortgageAmount(Mortgage mortgage) {
        if(controleDeSaisie(mortgage).length()>0){
            return(controleDeSaisie(mortgage));
        }
        else{
            MortgageParameters mp=mortgageParametersRepository
                    .findAll().stream()
                    .sorted(Comparator.comparing(MortgageParameters::getIdMortgageParameters))
                    .collect(Collectors.toList()).get(mortgageParametersRepository.findAll().size()-1);
            if(mp!=null){
                System.out.println(mp.getInterestRate()+" "+ mp.getPropertyTaxRate());
                mortgage.setMortgageParameters(mp);
                double monthlyRate = mortgage.getMortgageParameters().getInterestRate() / (12*100);
                int n = mortgage.getLoanPeriode() * 12;
                double loanAmount = mortgage.getLoanAmount() - mortgage.getDownPayment();
                System.out.println("P="+loanAmount+ " r="+monthlyRate+" n="+n);
                double numerator = loanAmount * monthlyRate * Math.pow(1 + monthlyRate, n);
                double denominator = Math.pow(1 + monthlyRate, n) - 1;
                double monthlyPayment = numerator / denominator;

                mortgageRepository.save(mortgage);
                return "Your monthly Payment is:"+monthlyPayment;
            }
            return "mortgage parameters not found";

        }

    }
    public String controleDeSaisie(Mortgage mortgage){
        BankParameters bp=bankParametersRepository
                .findAll().stream()
                .sorted(Comparator.comparing(BankParameters::getId))
                .collect(Collectors.toList()).get(bankParametersRepository.findAll().size()-1);
        String erreurs="";
        if(mortgage.getLoanPeriode()>bp.getLoanPeriodeMax() || mortgage.getLoanPeriode()<bp.getLoanPeriodeMin()){
            erreurs+="loan periode must be between "+bp.getLoanPeriodeMin()+" and"+bp.getLoanPeriodeMax()+"\n";
        }
        if(mortgage.getLoanAmount()>bp.getLoanMax() || mortgage.getLoanAmount()<bp.getLoanMin()){
            erreurs+="loan amount must be between "+bp.getLoanMin()+" and"+bp.getLoanMax()+"\n";
        }
        if(mortgage.getDownPayment()>mortgage.getLoanAmount()*bp.getDownPaymentMax() || mortgage.getLoanAmount()<mortgage.getLoanAmount()*bp.getDouwnPaymentMin()){
            erreurs+="Down payment must be between "+bp.getDouwnPaymentMin()+" and"+bp.getDownPaymentMax()+"\n";
        }
        if(mortgage.getLoanType().isEmpty()){
            erreurs+="loan type empty\n";
        }

        return erreurs;
    }
    public Double getAvreageLoanAmountByType(String loanType){
        return mortgageRepository.getAvreageLoanAmountByType(loanType);
    }

}
