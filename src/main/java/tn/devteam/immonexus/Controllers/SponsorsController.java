package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.Sponsors;
import tn.devteam.immonexus.Interfaces.ISponsorsService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Sponsors")
public class SponsorsController {
    ISponsorsService iSponsorsService;


    @PostMapping("/add-Sponsor")
    public Sponsors addSponsor(@RequestBody Sponsors sponsor){

        return iSponsorsService.addSponsor(sponsor);
    }

    @GetMapping("/get-AllSponsors")
    public List<Sponsors> getAllSponsors(){
        return iSponsorsService.getAllSponsors();

    }

    @GetMapping("/get-Sponsor-ById/{idS}")

    public Sponsors getSponsorById(@PathVariable("idS") Long IdS){

        return iSponsorsService.getSponsorById(IdS);

    }

    @PutMapping("/update-Sponsor")

    public Sponsors updateSponsor(@RequestBody Sponsors s) {
        return iSponsorsService.updateSponsor(s);

    }

    @DeleteMapping("/delete-Sponsor-ById/{idSp}")

    public void deleteById(@PathVariable("idSp") Long idSponsor) {
        iSponsorsService.deleteById(idSponsor);

    }

    @DeleteMapping("/delete-All-Sponsors")
    public void deleteAll() {
        iSponsorsService.deleteAll();


    }




    }
