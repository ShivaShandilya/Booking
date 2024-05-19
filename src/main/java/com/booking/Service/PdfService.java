package com.booking.Service;

import com.booking.Dto.BookingDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.stream.Stream;

@Service
public class PdfService {
    public boolean generatePdf( String filname,BookingDto bookingDto) {
        try {


            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filname));


            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Hello World", font);

            document.add(chunk);

            document.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}







