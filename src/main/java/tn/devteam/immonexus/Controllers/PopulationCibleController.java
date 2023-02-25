package tn.devteam.immonexus.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.PopulationCible;
import tn.devteam.immonexus.Interfaces.IPopulationCibleService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/PopulationCible")

public class PopulationCibleController {


IPopulationCibleService iPopulationCibleService;


    @PostMapping("/add-PopulationCible")

    PopulationCible addPopulationCible(@RequestBody PopulationCible populationCible){
        return iPopulationCibleService.addPopulationCible(populationCible);

    }

    @GetMapping("/get-PopulationCible")
    List<PopulationCible> getAllPopulationCible(){
        return iPopulationCibleService.getAllPopulationCible();

    }

    @GetMapping("/get-PopulationCible-ById/{idPop}")

    PopulationCible getPopulationCibleById(@PathVariable("idPop") Long idPop){

        return iPopulationCibleService.getPopulationCibleById(idPop);

    }

    @PutMapping("/update-PopulationCible")

    PopulationCible updatePopulationCible(@RequestBody  PopulationCible populationCible){
        return iPopulationCibleService.updatePopulationCible(populationCible);

    }

    @DeleteMapping("/delete-PopulationCible-ById/{idPop}")

    void deleteById(@PathVariable("idPop") Long idPopulationCible){
        iPopulationCibleService.deleteById(idPopulationCible);

    }
    @DeleteMapping("/delete-All-PopulationCible")
    void deleteAll(){
        iPopulationCibleService.deleteAll();

    }
    @PutMapping("/affect-PopulationCible-To-Advertising/{idPopulationCible}/{idAd}")

    public void affectPopulationCibleToAdvertising(@PathVariable("idPopulationCible") Long idPopulationCible,
                                                   @PathVariable("idAd")Long idAd) {
        iPopulationCibleService.affectPopulationCibleToAdvertising(idPopulationCible,idAd);

    }

    }
