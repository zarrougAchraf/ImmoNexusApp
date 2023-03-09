package tn.devteam.immonexus.Controllers;

import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xhtmlrenderer.pdf.ITextRenderer;
import tn.devteam.immonexus.Entities.Mortgage;
import tn.devteam.immonexus.Entities.MortgageParameters;
import tn.devteam.immonexus.Interfaces.IMortgageParametersService;
import tn.devteam.immonexus.Interfaces.IMortgageService;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Mortgage")
public class MortgageController {
    @Autowired
    private IMortgageService iMortgageService;
    @Autowired
    private IMortgageParametersService iMortgageParametersService;
    @PostMapping("/add")
    String addMortgage(@RequestBody Mortgage mortgage){
        return iMortgageService.addMortgage(mortgage);
    }
    @PutMapping("/update")
    void updateMortgage(@RequestBody Mortgage mortgage){
        iMortgageService.updateMortgage(mortgage);
    }
    @DeleteMapping("/delete/{id}")
    void deleteMortgage(@PathVariable Long id){
        iMortgageService.deleteMortgage(id);
    }
    @GetMapping("/display")
    List<Mortgage> displayMortgages(){
        return iMortgageService.displayMortgages();
    }
    @PostMapping("/addMortgageParameters")
    void addMortgageParameter(@RequestBody MortgageParameters mortgageParameters){
        iMortgageParametersService.addMortgageParameters(mortgageParameters);
    }
    @GetMapping("/displayMaxMortgage")
    String displayMortgagesResult(@RequestBody Mortgage mortgage){
        return iMortgageService.calculateMaxMortgageAmount(mortgage);
    }

    @GetMapping(value="/displayMaxMortgagePDF",produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<byte[]> displayMortgagesResultPDF(@RequestBody Mortgage mortgage) throws DocumentException {
        String html="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\"></meta>\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Mortgage Calculator</h1>\n" +
                "<p>"+iMortgageService.calculateMaxMortgageAmount(mortgage)+"</p>\n" +
                "</body>\n" +
                "</html>";
        ITextRenderer renderer=new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        renderer.createPDF(byteArrayOutputStream);
        byte[] bytes=byteArrayOutputStream.toByteArray();
        HttpHeaders header=new HttpHeaders();
        header.add("Content-Disposition","attachment; filename=mypdf.pdf");
        return ResponseEntity.ok().headers(header).contentType(MediaType.APPLICATION_PDF).body(bytes);
    }
    @GetMapping("/getAvreageLoanAmountByType/{loanType}")
    public Double getAvreageLoanAmountByType(@PathVariable String loanType){
        return iMortgageService.getAvreageLoanAmountByType(loanType);
    }
}
