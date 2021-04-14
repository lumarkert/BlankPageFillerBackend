package de.lukasmarkert.BlankPageFillerBackend.Pdf;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;


@RestController
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/pdf")
    public String singlePdf() {

        return "Greetings from Spring Boot!";
    }

    @CrossOrigin(origins = "https://lukasmarkert.de")
    @PostMapping(value = "/pdf", consumes = "multipart/form-data")
    public ResponseEntity<byte[]> addBlankPagesToPdf(@NotNull @RequestParam("file") MultipartFile pdf) throws Exception {
        if (!(pdfService.checkIfPdf(pdf))) {
            throw new Exception();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData(pdfService.changeFileName(pdf.getOriginalFilename()), pdfService.changeFileName(pdf.getOriginalFilename()));
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(
                pdfService.addBlankPages(pdf), headers, HttpStatus.OK);
    }
}
