package tn.devteam.immonexus.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.BankParameters;
import tn.devteam.immonexus.Interfaces.IBankParametersService;
import tn.devteam.immonexus.Repository.BankParametersRepository;

@Service
public class BankParametersService implements IBankParametersService {
    @Autowired
    private BankParametersRepository bankParametersRepository;
    @Override
    public void addBankParameters(BankParameters bankParameters) {
        bankParametersRepository.save(bankParameters);
    }
}
