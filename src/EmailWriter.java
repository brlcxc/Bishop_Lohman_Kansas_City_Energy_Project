import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//this code is taken primarily from the provided example for writing a pdf
public class EmailWriter {
    private static final String FILE_NAME = "invoice.pdf";

    public static void writeUsingIText(String emailInput) {

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            //open
            document.open();

            Paragraph h = new Paragraph();
            h.add("Kansas City Energy Cooperative\n");
            h.setAlignment(Element.ALIGN_CENTER);

            document.add(h);

            Paragraph p = new Paragraph();
            p.add(emailInput);
            p.setAlignment(Element.ALIGN_LEFT);

            document.add(p);

            //close
            document.close();

            System.out.println("Done");
        }
        catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }
}