package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.Announcement;
import tn.devteam.immonexus.Interfaces.IAnnouncementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Announcement")
public class AnnouncementController {
    IAnnouncementService iAnnouncementService;

    @PostMapping("/addAnnounce")
    public Announcement addAnnouncement(@RequestBody Announcement a) {
        return iAnnouncementService.addAnnouncement(a);
    }

    @PutMapping("/updateAnnounce")
    public Announcement updateAnnouncement(@RequestBody Announcement a) {
        return iAnnouncementService.updateAnnouncement(a);
    }

    @GetMapping("/get-Announce/{ida}")
    public Announcement retrieveAnnouncement(@PathVariable("ida") Long ida) {
        return iAnnouncementService.retrieveAnnouncement(ida);
    }

    @DeleteMapping("/Remove-par-Id/{idA}")
    public void removeAnnouncement(@PathVariable("idA") Long idA) {
         iAnnouncementService.removeAnnouncement(idA);
    }
    @DeleteMapping("/Remove-All")
    public void removeAll() {
        iAnnouncementService.removeAll();
    }

    @GetMapping("/get-All-Announcement")
   public List<Announcement> retrieveAllAnnoucement(){
        return iAnnouncementService.retrieveAllAnnoucement();
    }

}
