package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.devteam.immonexus.Entities.BankParameters;
import tn.devteam.immonexus.Interfaces.IBankParametersService;

@RestController
@AllArgsConstructor
@RequestMapping("/BankParameters")
public class BankParamtersController {
    @Autowired
    private IBankParametersService iBankParametersService;
    @PostMapping("/add")
    void addBankParameters(@RequestBody BankParameters bankParameters){
        iBankParametersService.addBankParameters(bankParameters);
    }
}
