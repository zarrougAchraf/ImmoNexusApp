
package tn.devteam.immonexus.Controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.devteam.immonexus.Entities.Advertising;
import tn.devteam.immonexus.Entities.Canaux;
import tn.devteam.immonexus.Entities.DateValidationException;
import tn.devteam.immonexus.Entities.Sponsors;
import tn.devteam.immonexus.Interfaces.IAdvertisingService;
import tn.devteam.immonexus.Interfaces.IFileUploadService;
import tn.devteam.immonexus.Repository.AdevertisingRepository;
import tn.devteam.immonexus.Repository.SponsorsRepository;
import tn.devteam.immonexus.Services.EmailService;

import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/Advertising")
@Slf4j
public class AdvertisingController {
    IAdvertisingService iAdvertisingService;
    IFileUploadService iFileUploadService;
    @Autowired
    AdevertisingRepository adevertisingRepository;
    @Autowired
    private EmailService emailsend;
    @Autowired
    SponsorsRepository sponsorsRepository;

    @PostMapping("/add-Advertising/")
    public Advertising addAdvertisingg(@RequestParam("title") String title,
                                       @RequestParam("description") String description ,
                                       @RequestParam("canaux") Canaux canaux,
                                       @RequestParam("nbrVuesCible") double nbrVuesCible,
                                       @RequestParam("coutParJour") double coutParJour,
                                       @RequestParam("coutParVueCible") double coutParVueCible,
                                       @RequestParam("image") MultipartFile image,
                                       @RequestParam("idSponsor") Long idSponsor,

                                       @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate

                                       ) throws IOException {

         LocalDate startDate=LocalDate.now();
        if (endDate.isBefore(startDate)) {
            throw new DateValidationException("La date de fin doit être postérieure à la date de début.");
        }
       Advertising advertising=new Advertising();
       advertising.setDescription(description);
        advertising.setCanaux(canaux);
        advertising.setTitle(title);

       advertising.setStartDate(startDate);
       advertising.setEndDate(endDate);

        //log.info("seiiiif :"+image.getOriginalFilename());
        iFileUploadService.uploadfile(image);
        advertising.setImage(image.getOriginalFilename());

        Long nbrJours = iAdvertisingService.calculerNbreDesJours(advertising);
        advertising.setNbrJours(nbrJours);

        advertising.setNbrVuesCible(nbrVuesCible);

        advertising.setCoutParVueCible(coutParVueCible);
        advertising.setCoutParJour(coutParJour);

        double gainPublicitaire = iAdvertisingService.calculerGainPublicitaire(advertising);
        advertising.setGainPublicitaire(gainPublicitaire);

        Sponsors sponsor=sponsorsRepository.findById(idSponsor).orElse(null);
        advertising.setSponsor(sponsor);


       try {
            emailsend.sendEmail(advertising.getSponsor().getEmail(),
                    "a propos l'ajout de votre pub",
                    "Bonjour " + advertising.getSponsor().getName()
                            + "  votre publicite a ete publiee et elle sera diponible dans notre site "
                            + advertising.getNbrJours() +" jours du " + advertising.getStartDate() +
                            " à "+ advertising.getEndDate());
        } catch (SendFailedException e) {
            throw new RuntimeException(e);
        }
        return iAdvertisingService.addAdvertising(advertising);

    }
//afficher juste les valides pub

    @GetMapping("/get-AllAdvertising")
    public ResponseEntity<?> getAllAdvertising(HttpServletRequest request){


        LocalDate currentDate = LocalDate.now();
        List<Advertising> advertisings = iAdvertisingService.getAllAdvertising(request);
//reversed a été utilisée pour trier par ordre décroissant

        List<Advertising> validAdvertisings = advertisings.stream()
                .filter(advertising -> advertising.getEndDate().isAfter(currentDate))
                .sorted(Comparator.comparingDouble(Advertising::getGainPublicitaire).reversed())
                .collect(Collectors.toList());

        if (!advertisings.isEmpty()) {
            return ResponseEntity.ok(validAdvertisings);
        } else {
            String message = "Aucune publicité trouvée ";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

    }

// get all pub valid et non valid
    @GetMapping("/get-All-Actual-Advertising/{start}/{end}")
    public ResponseEntity<?> getAllActualAdvertising(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                     @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                     HttpServletRequest request){


        if (endDate.isBefore(startDate)) {
            throw new DateValidationException("La date de fin doit être postérieure à la date de début.");
        }

        LocalDate currentDate = LocalDate.now();
        List<Advertising> advertisings = iAdvertisingService.getAllActualAdvertising(startDate, endDate,request);


        List<Advertising> validAdvertisings = advertisings.stream()
                .filter(advertising -> advertising.getEndDate().isAfter(currentDate))
                .collect(Collectors.toList());


        if (!advertisings.isEmpty()) {
            return ResponseEntity.ok(validAdvertisings);
        } else {
            String message = "Aucune publicité trouvée pour les dates spécifiées";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @GetMapping("/nbrAdvertisingsBySponsor")
    public String nbrAdvertisingsBySponsor() {

        return iAdvertisingService.nbrAdvertisingsBySponsor();
    }
    @GetMapping("/export-pdf/{id}")
    public ResponseEntity<ByteArrayResource> exportAdvertisingToPdf(@PathVariable Long id) throws IOException, DocumentException {
        // Récupérer la publicité à exporter
        Advertising advertising = adevertisingRepository.findById(id).orElse(null);

        // Créer un nouveau document PDF
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        Font font = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.RED);
        // Ouvrir le document
        document.open();

        // Ajouter le titre de la publicité
        Paragraph title = new Paragraph(advertising.getTitle(),font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph space = new Paragraph("\n");
        document.add(space);

        // Ajouter les détails de la publicité
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        PdfPCell cell;

        // Ajouter le Description de la publicité
        cell = new PdfPCell(new Phrase("name Of Sponsor"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(advertising.getSponsor().getName()));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Email Of Sponsor"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(advertising.getSponsor().getEmail()));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("PhoneNumber Of Sponsor"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(advertising.getSponsor().getPhoneNumber()));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Description"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(advertising.getDescription()));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Start Date"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(advertising.getStartDate().toString()));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("End Date"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(advertising.getEndDate().toString()));
        table.addCell(cell);



        cell = new PdfPCell(new Phrase("nbrJours"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Long.toString(advertising.getNbrJours())));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("coutParJour"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Double.toString(advertising.getCoutParJour())));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("NbrVuesCible"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Double.toString(advertising.getNbrVuesCible())));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("coutParVueCible"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Double.toString(advertising.getCoutParVueCible())));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("gainPublicitaire"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Double.toString(advertising.getGainPublicitaire())));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("nbrVuesFinal"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Double.toString(advertising.getNbrVuesFinal())));
        table.addCell(cell);



        document.add(table);

        // Fermer le document
        document.close();

        // Créer une ressource ByteArray pour le contenu du PDF
        byte[] bytes = outputStream.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(bytes);

        // Retourner une réponse avec le contenu du PDF
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=advertising.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(bytes.length)
                .body(resource);
    }


   /* @GetMapping("/get-Advertising-ById/{idA}")
    public Advertising getAdvertisingById(@PathVariable("idA") Long idAd)
    {
        return iAdvertisingService.getAdvertisingById(idAd);
    }*/

 /*   @PutMapping("/update-Advertising")
    public Advertising updateAdvertising(@RequestBody Advertising add)
    {

        return iAdvertisingService.updateAdvertising(add);
    }*/

    @DeleteMapping("/delete-Advertising-ById/{idA}")
    public void deleteById(@PathVariable("idA") Long idAdvertising) {

        iAdvertisingService.deleteById(idAdvertising);
    }


    @DeleteMapping("/delete-All-Advertising")
    public void deleteAll()
    {

        iAdvertisingService.deleteAll();
    }








    }
