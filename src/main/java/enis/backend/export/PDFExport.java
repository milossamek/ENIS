package enis.backend.export;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.lowagie.text.pdf.PdfWriter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.vaadin.server.StreamResource;

import enis.backend.model.Vuz;


public class PDFExport {
    private List<Vuz> vozy;

    public PDFExport() {
    }

    public List<Vuz> getVozy() {
        return vozy;
    }

    public void setVozy(List<Vuz> vozy) {
        this.vozy = vozy;
    }

    public StreamResource getPDFStream() {
        StreamResource.StreamSource source = new StreamResource.StreamSource() {

			private static final long serialVersionUID = 9021723519704227161L;

			public ByteArrayInputStream getStream() {
                Document document = new Document();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                try {
                    PdfWriter writer = PdfWriter.getInstance(document, baos);
                    document.open();
                    Paragraph paragraph = new Paragraph();
                    paragraph.add("ENIS system");
                    paragraph.setAlignment(Element.ALIGN_CENTER);
                    Paragraph otherParagraph = new Paragraph();
                    otherParagraph.add("");
                    otherParagraph.setAlignment(Element.ALIGN_CENTER);
                    document.add(paragraph);
                    document.add(otherParagraph);
                    PdfPTable table = new PdfPTable(new float[] { 5, 5, 5});
                    for (Vuz vuz : vozy) {
                        table.addCell("Cislo vozu: " + vuz.getVuzCis());
                        table.addCell("Max rychlost: " + vuz.getMaxRychlost());
                        table.addCell("Hmotnost: " + vuz.getVlastniHmotnost());
                    }
                    document.add(table);
                    document.close();
                } catch (DocumentException ex) {
                    // Logger.getLogger(PDFExport.class.getName()).log(Level.SEVERE, null, ex);
                }

                ByteArrayOutputStream stream = baos;
                ByteArrayInputStream input = new ByteArrayInputStream(stream.toByteArray());
                return input;

            }
        };
        StreamResource resource = new StreamResource(source, "test.pdf");
        return resource;
    }



}
