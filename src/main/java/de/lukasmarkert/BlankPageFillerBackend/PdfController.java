package de.lukasmarkert.BlankPageFillerBackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/pdf")
    public String singlePdf() {
        pdfService.addBlankPages();
        return "Greetings from Spring Boot!";
    }
}
