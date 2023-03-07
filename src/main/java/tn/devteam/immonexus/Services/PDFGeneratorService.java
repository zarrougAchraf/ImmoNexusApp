package tn.devteam.immonexus.Services;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Visit;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class PDFGeneratorService {



    public void export(Visit visit, HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph title = new Paragraph("Visit details  " , fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);


        Paragraph date = new Paragraph("Visit date  : " + visit.getVisitDate(), fontParagraph);
        Paragraph duration = new Paragraph("Visit duration  : " + visit.getDuration(), fontParagraph);
        Paragraph type = new Paragraph("Visit type  : " + visit.getVisitType(), fontParagraph);
        Paragraph status = new Paragraph("Visit status  : " + visit.getVisitStatus(), fontParagraph);
        Paragraph price = new Paragraph("Visit price  : " + visit.getVisitPrice(), fontParagraph);



        document.add(title);
        document.add(date);
        document.add(duration);
        document.add(type);
        document.add(status);
        document.add(price);

        document.close();

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"" + visit.getIdVisit() + ".pdf\"");

       // response.getOutputStream().write(Files.readAllBytes(Paths.get(visit.getIdVisit() + ".pdf")));
        response.getOutputStream().flush();
    }



}