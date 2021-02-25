package de.lukasmarkert.BlankPageFillerBackend.Pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.*;


@Service
public class PdfService {
    public String changeFileName(String oldFileName) {
        return "Notizen_" + oldFileName;
    }

    public boolean checkIfPdf(MultipartFile pdf) {
        System.out.println(pdf.getContentType());
        return "application/pdf".equals(pdf.getContentType());
    }

    public byte[] addBlankPages(MultipartFile pdf) {
        try {
            PdfReader inputPdfReader = new PdfReader(pdf.getBytes());
            PdfReader.unethicalreading = true;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(inputPdfReader, baos);
            for (int i = 2; i <= inputPdfReader.getNumberOfPages() + 1; i = i + 2) {
                stamper.insertPage(i, inputPdfReader.getPageSizeWithRotation(1));
                System.out.println("Inserted Page at: " + i);
            }
            stamper.close();
            inputPdfReader.close();
            return baos.toByteArray();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }

}
