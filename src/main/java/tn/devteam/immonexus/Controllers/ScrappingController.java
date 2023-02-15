package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.devteam.immonexus.Interfaces.IScrapingService;

@RestController
@AllArgsConstructor
@RequestMapping("/Scraping")
public class ScrappingController {
    IScrapingService iScrapingService;
}
