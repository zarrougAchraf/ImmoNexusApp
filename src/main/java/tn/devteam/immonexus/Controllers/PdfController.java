package tn.devteam.immonexus.Controllers;

import com.lowagie.text.DocumentException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/pdf")
public class PdfController {
    @Autowired
    ResourceLoader resourceLoader;
    @GetMapping(value = "/generate",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf() throws IOException, DocumentException {

        String html="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\"></meta>\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Default title</h1>\n" +
                "<p>default content</p>\n" +
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
    public String getHtmlContent(HttpServletRequest request) throws IOException {
        Resource resource=resourceLoader.getResource("classpath:/my-template.html");
        Document document= Jsoup.parse(resource.getInputStream(),"UTF-8","");

        Element body =document.body();
        Elements elements=body.select("div");
        elements.remove();
        return body.html();

    }

}
