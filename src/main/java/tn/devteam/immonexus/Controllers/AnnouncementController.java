package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.devteam.immonexus.Interfaces.IAnnouncementService;

@RestController
@AllArgsConstructor
@RequestMapping("/Announcement")
public class AnnouncementController {
    IAnnouncementService iAnnouncementService;
}
