package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.Affordability;
import tn.devteam.immonexus.Interfaces.IAffordabilityService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Affordability")
public class AffordbilityController {
    @Autowired
    private IAffordabilityService iAffordabilityService;
    @PostMapping("/add")
    void addAffordability(@RequestBody Affordability affordability){
        iAffordabilityService.addAffordability(affordability);
    }
    @PutMapping("/update")
    void updateAffordability(@RequestBody Affordability affordability){
        iAffordabilityService.updateAffordability(affordability);
    }
    @DeleteMapping("/delete/{id}")
    void deleteAffordability(@PathVariable Long id){
        iAffordabilityService.deleteAffordability(id);
    }
    @GetMapping("/display")
    List<Affordability> displayAffordabilitys(){
        return iAffordabilityService.displayAffordabilities();
    }
    @GetMapping("/calculateAffordability")
    double calculateAffordability(@RequestBody Affordability affordability){
        return iAffordabilityService.calculateAfforadability(affordability);
    }


}
