package tn.devteam.immonexus.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.devteam.immonexus.Entities.Visit;
import tn.devteam.immonexus.Services.PDFGeneratorService;
import tn.devteam.immonexus.Services.VisitService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PDFExportController {

    @Autowired
    VisitService visitService;

    private final PDFGeneratorService pdfGeneratorService;

    public PDFExportController(PDFGeneratorService pdfGeneratorService , VisitService visitService) {
        this.pdfGeneratorService = pdfGeneratorService;
        this.visitService=visitService;
    }

    @GetMapping("/pdf/generate/{visitId}")
    public void generatePDF(@PathVariable Long visitId, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);



        Visit visit = visitService.getVisitById(visitId);
        this.pdfGeneratorService.export(visit, response);
    }

}
