package tn.devteam.immonexus.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.MortgageParameters;
import tn.devteam.immonexus.Interfaces.IMortgageParametersService;
import tn.devteam.immonexus.Repository.MortgageParametersRepository;

import java.util.List;
@Service
public class MortgageParametersService implements IMortgageParametersService {
    @Autowired
    MortgageParametersRepository mortgageParametersRepository;
    @Override
    public void addMortgageParameters(MortgageParameters mortgageParameters) {
        mortgageParametersRepository.save(mortgageParameters);
    }

    @Override
    public void updateMortgageParameters(MortgageParameters mortgageParameters) {
        mortgageParametersRepository.save(mortgageParameters);
    }

    @Override
    public void deleteMortgageParameters(Long id) {
        mortgageParametersRepository.deleteById(id);
    }

    @Override
    public List<MortgageParameters> displayMortgageParameterss() {
        return mortgageParametersRepository.findAll();
    }
}
