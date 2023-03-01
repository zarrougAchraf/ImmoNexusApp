package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.devteam.immonexus.Entities.Advertising;
import tn.devteam.immonexus.Interfaces.IAdvertisingService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/Advertising")
public class AdvertisingController {
    IAdvertisingService iAdvertisingService;
    @PostMapping("/add-Advertising")
    public Advertising addAdvertising(@RequestBody Advertising ad){

        return iAdvertisingService.addAdvertising(ad);
    }
   /* @PostMapping("/upload")
    public Advertising handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            Advertising advertising = new Advertising ();

            //newUser.setAdresse ("Tunis");
            //newUser.setEmail ("user@gmail.com");
            //newUser.setImage (file.getBytes());
            advertising.setDescription("fffff");
            advertising.setImage (file.getBytes());
            iAdvertisingService.addAdvertising (advertising);
            return advertising;
        } catch (IOException e) {
            e.printStackTrace ();
            throw new RuntimeException (e);
        }
    }*/

    @GetMapping("/get-AllAdvertising")
    public List<Advertising> getAllAdvertising(){

        return iAdvertisingService.getAllAdvertising();

    }
/*
    @GetMapping("/get-All-Actual-Advertising/{start}/{end}")
    public ResponseEntity<?> getAllActualAdvertising(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                          @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){

        List<Advertising> advertisings = iAdvertisingService.getAllActualAdvertising(startDate, endDate);

      //  return iAdvertisingService.getAllActualAdvertising(startDate,endDate);

        if (advertisings.isEmpty()) {
            Map<String, String> message = new HashMap<>();
            message.put("message", "Aucune publicité trouvée pour les dates spécifiées");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);


        } else {
            return ResponseEntity.ok(advertisings);
        }

    }*/

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

  /*  @PostMapping("/add-Publicite")
    public String addPublicite(@RequestBody Advertising p ){
        return iAdvertisingService.addPublicite(p);
    }*/
/*
    @GetMapping("/get-AllPublicite")
    public List<Advertising> getAllPublicite(){
        return  iAdvertisingService.getAllPublicite();
    }

    @DeleteMapping("/delete-Pub-ById/{idPub}")

    public void deletePub( @PathVariable("idPub") Long id){

        iAdvertisingService.deletePub(id);
    }*/

    /*
    @GetMapping("/get-testSimplex/{idAdv}")
    public double testSimplex(@PathVariable("idAdv")Long id){
     return    iAdvertisingService.testSimplex(id);
    }

    @GetMapping("/get-tarifPubCaneaux/{idPub}")
    public double tarifPubCaneaux(@PathVariable("idPub") Long idPub) {
        return iAdvertisingService.tarifPubCaneaux(idPub);
    }

    @GetMapping("/get-tarifPubParAge/{idPop}")

    public double tarifPubParAge(@PathVariable("idPop") Long idPop) {
        return iAdvertisingService.tarifPubParAge(idPop);
    }

    @GetMapping("/get-tarifPubParGender/{idPop}")

    public double tarifPubParGender(@PathVariable("idPop") Long idPop) {
return  iAdvertisingService.tarifPubParGender(idPop);
    }


    @GetMapping("/get-tarifPubParProfession/{idPop}")

    public double tarifPubParProfession(@PathVariable("idPop")Long idPop) {
return  iAdvertisingService.tarifPubParProfession(idPop);
    }*/

    @GetMapping("/get-maxGain/{idAdv}")

    public String maxGain(@PathVariable("idAdv")Long id) {
        return  iAdvertisingService.maxGain(id);

    }

    @GetMapping("/nbrAdvertisingsBySponsor")
    public String nbrAdvertisingsBySponsor() {
        return iAdvertisingService.nbrAdvertisingsBySponsor();
    }



    }
