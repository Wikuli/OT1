package oma.grafiikka.ot1;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
public class luoPdf {
    public static void luoPdfDoc(String laskunNimi, String tekstiLaskuun) {
        String filePath = laskunNimi;

        try {
            // Luodaan PdfWriter
            PdfWriter writer = new PdfWriter(filePath);

            // Luo PdfDocument
            PdfDocument pdf = new PdfDocument(writer);

            // Luo Document-objekti
            Document document = new Document(pdf);

            // Lisää sisältöä dokumenttiin
            document.add(new Paragraph(tekstiLaskuun));

            // Sulje dokumentti
            document.close();

            System.out.println("PDF-dokumentti luotu: " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Virhe: Tiedostoa ei löydy.");
            e.printStackTrace();
        }
    }
}
