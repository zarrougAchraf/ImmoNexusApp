package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.Advertising;
import tn.devteam.immonexus.Interfaces.IAdvertisingService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Advertising")
public class AdvertisingController {
    IAdvertisingService iAdvertisingService;
    @PostMapping("/add-Advertising")
    public Advertising addAdvertising(@RequestBody Advertising ad){

        return iAdvertisingService.addAdvertising(ad);
    }

    @GetMapping("/get-AllAdvertising")
    public List<Advertising> getAllAdvertising(){

        return iAdvertisingService.getAllAdvertising();

    }

    @GetMapping("/get-Advertising-ById/{idA}")
    public Advertising getAdvertisingById(@PathVariable("idA") Long idAd)
    {
        return iAdvertisingService.getAdvertisingById(idAd);
    }

    @PutMapping("/update-Advertising")
    public Advertising updateAdvertising(@RequestBody Advertising add)
    {

        return iAdvertisingService.updateAdvertising(add);
    }

    @DeleteMapping("/delete-Advertising-ById/{idA}")
    public void deleteById(@PathVariable("idA") Long idAdvertising) {

        iAdvertisingService.deleteById(idAdvertising);
    }


    @DeleteMapping("/delete-All-Advertising")
    public void deleteAll()
    {

        iAdvertisingService.deleteAll();
    }

    @PutMapping("/affect-Advertising-To-Sponsor/{idSponsor}/{idAd}")
    public void affectAdvertisingToSponsor(@PathVariable("idSponsor") Long idSponsor,
                                           @PathVariable("idAd") Long idAd){
        iAdvertisingService.affectAdvertisingToSponsor(idSponsor,idAd);
    }

    }
