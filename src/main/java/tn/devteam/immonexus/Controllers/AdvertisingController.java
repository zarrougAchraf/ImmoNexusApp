package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.devteam.immonexus.Entities.Advertising;
import tn.devteam.immonexus.Interfaces.IAdvertisingService;
import tn.devteam.immonexus.Interfaces.IFileUploadService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/Advertising")
@Slf4j
public class AdvertisingController {
    IAdvertisingService iAdvertisingService;
    IFileUploadService iFileUploadService;

    @PostMapping("/add-Advertising/")
    public Advertising addAdvertisingg(@RequestParam("title") String title,
                                       @RequestParam("description") String description ,
                                       @RequestParam("nbrVuesCible") double nbrVuesCible,
                                       @RequestParam ("nbrVuesFinal") double nbrVuesFinal,
                                        @RequestParam("image") MultipartFile image,
                                       @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate

                                       ) throws IOException {

         LocalDate startDate=LocalDate.now();
       Advertising advertising=new Advertising();
       advertising.setDescription(description);
       advertising.setTitle(title);
       advertising.setNbrVuesCible(nbrVuesCible);
       advertising.setNbrVuesFinal(nbrVuesFinal);
       advertising.setStartDate(startDate);
       advertising.setEndDate(endDate);
        iFileUploadService.uploadfile(image);
//log.info("seiiiif :"+image.getOriginalFilename());

        advertising.setImage(image.getOriginalFilename());

        return iAdvertisingService.addAdvertising(advertising);

    }

    @GetMapping("/get-AllAdvertising")
    public List<Advertising> getAllAdvertising(){

        return iAdvertisingService.getAllAdvertising();

    }


    @GetMapping("/get-All-Actual-Advertising/{start}/{end}")
    public ResponseEntity<?> getAllActualAdvertising(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                     @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){

        List<Advertising> advertisings = iAdvertisingService.getAllActualAdvertising(startDate, endDate);

        if (!advertisings.isEmpty()) {
            return ResponseEntity.ok(advertisings);
        } else {
            String message = "Aucune publicité trouvée pour les dates spécifiées";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @GetMapping("/nbrAdvertisingsBySponsor")
    public String nbrAdvertisingsBySponsor() {

        return iAdvertisingService.nbrAdvertisingsBySponsor();
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


    @GetMapping("/get-maxGain/{idAdv}")

    public String maxGain(@PathVariable("idAdv")Long id) {
        return  iAdvertisingService.maxGain(id);

    }




    }
