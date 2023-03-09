package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.devteam.immonexus.Entities.Announcement;
import tn.devteam.immonexus.Entities.RealEstateType;
import tn.devteam.immonexus.Entities.TypeOffer;
import tn.devteam.immonexus.Interfaces.IAnnouncementService;
import tn.devteam.immonexus.Interfaces.IFileUploadService;
import tn.devteam.immonexus.Interfaces.IImageVerificationService;
import tn.devteam.immonexus.Interfaces.ILikeService;
import tn.devteam.immonexus.Services.twilio.SmsServiceImpl;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/Announcement")
public class AnnouncementController {
    @Autowired
    IAnnouncementService iAnnouncementService;
    @Autowired

    IFileUploadService iFileUploadService;
    @Autowired

    IImageVerificationService iImageVerificationService;
    @Autowired
    SmsServiceImpl smsService;
    @Autowired
    ILikeService likeService;
    @PostMapping("/addAnnounce/")
    public Announcement addAnnouncement(@RequestParam("dateEvenement") double price, @RequestParam("surface") double surface,
                                        @RequestParam("titre") String titre, @RequestParam("lieux") TypeOffer offer,
                                        @RequestParam("description") String description, @RequestParam("affiche") MultipartFile affiche,
                                        @RequestParam("coveredArea") double coveredArea,
                                        @RequestParam("realEstateType") RealEstateType realEstateType
    )
            throws ParseException, IOException, InterruptedException {
        LocalDate pubdate=LocalDate.now();
        Announcement announcement = new Announcement();
        //  smsService.SendSMS();
        announcement.setPublicationDate(pubdate);
        announcement.setTotalSurface(surface);
        announcement.setPrice(price);
        announcement.setTitre(titre);
        announcement.setPublicationDate(pubdate);
        announcement.setDescription(description);
        announcement.setOfferType(offer);
        announcement.setCoveredArea(coveredArea);
        announcement.setRealEstateType(realEstateType);
        log.info("aaaaaaaaaaaaa::::"+announcement.getImage());

        iFileUploadService.uploadfile(affiche);
//boolean v= iImageVerificationService.isRealEstateImage(affiche);
        String imagePath =affiche.getOriginalFilename();
       // boolean c= iImageVerificationService.isRealEstateImage2(imagePath);
     //   log.info("validation AI:  true or false :" +c);
       // announcement.setValidity(c);
        announcement.setImage(imagePath);
        return iAnnouncementService.addAnnouncement(announcement);
    }
    @PostMapping("/verifyImage")
    public ResponseEntity<String> verifyImage(@RequestParam("file") MultipartFile file) throws IOException {

        return iImageVerificationService.verifyImage(file);
    }

    @PutMapping("/updateAnnounce")
    public Announcement updateAnnouncement(@RequestBody Announcement a) {
        return iAnnouncementService.updateAnnouncement(a);
    }

    @GetMapping("/get-Announce/")
    public Announcement retrieveAnnouncement(@RequestParam("idAnnonce") Long ida) {
        return iAnnouncementService.retrieveAnnouncement(ida);
    }

    @DeleteMapping("/Remove-par-Id/")
    public void removeAnnouncement(@RequestParam("idAnnonce") Long idA) {
        iAnnouncementService.removeAnnouncement(idA);
    }
    @DeleteMapping("/Remove-All")
    public void removeAll() {
        iAnnouncementService.removeAll();
    }
    @PutMapping("/affectAnnonce-to-user/{idA}/{fName}/{lName}")
    public void affectAnnouncetoUser(@PathVariable("idA") Long idAnnonce,
                                     @PathVariable("fName") String firstName,
                                     @PathVariable("lName") String lastName){
        iAnnouncementService.affectAnnouncetoUser(idAnnonce,firstName,lastName);

    }
    @GetMapping("/get-All-Announcement")
    public List<Announcement> retrieveAllAnnoucement(){
        return iAnnouncementService.retrieveAllAnnoucement();
    }
    @GetMapping("/classer-Annonces/")
    public List<Announcement> classerAnnoncesParNoteMoyenne(){
        return iAnnouncementService.classerAnnoncesParNoteMoyenne();
    }
    @PostMapping("/Like-annoncee/")
    public void LikeAnnounce(@RequestParam("idAnnounce") Long idAnnounce,
                             @RequestParam("idUser") Long idUser){
        iAnnouncementService.LikeAnnounce(idAnnounce, idUser);
    }
    @GetMapping("/Get-Likes/")
    public Integer getLikes(@RequestParam("idAnnonce") Long idAnnonce){
        return iAnnouncementService.getLikes(idAnnonce);
    }
    @GetMapping("/Get-Recomended-Announcement/")
    public List<Announcement> recommanderAnnonces(@RequestParam("userId") Long userId){
        return likeService.recommanderAnnonces(userId);
    }

}
