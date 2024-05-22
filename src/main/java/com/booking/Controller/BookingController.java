package com.booking.Controller;

import com.booking.Dto.BookingDto;
import com.booking.Repository.BookingRepository;
import com.booking.Service.*;
import com.booking.entity.Booking;
import com.booking.entity.Property;
import com.booking.entity.PropertyUser;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    private BookingService bookingService;
    private BookingRepository bookingRepository;
    private PdfService pdfService;

    private TwilioService twilioService;
    private SendGridService sendGridService;




    public BookingController(BookingService bookingService, BookingRepository bookingRepository, PdfService pdfService, TwilioService twilioService, SendGridService sendGridService) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
        this.pdfService = pdfService;

        this.twilioService = twilioService;
        this.sendGridService = sendGridService;
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<String> addBooking(@RequestBody BookingDto bookingDto,
                                             @AuthenticationPrincipal PropertyUser user,
                                             @PathVariable long propertyId) {
        Booking booking = bookingService.cerateBooking(bookingDto, propertyId, user);
        return new ResponseEntity<>("confirm Booking", HttpStatus.CREATED);

    }
@PostMapping("/pdf")
public ResponseEntity<String>generatePdf(String filename,@RequestBody BookingDto bookingDto) throws IOException {
         filename ="C://oct//" + "ConfirmBooking" + bookingDto.getId() + ".pdf";
    boolean b = pdfService.generatePdf(filename,bookingDto);
    if(b){
      //  MultipartFile file=BookingController.convert(new File("C://oct//" + "ConfirmBooking" + bookingDto.getId() + ".pdf"));
       // String uploadedFile = bucketService.uploadFile(file, "myname4");
       // System.out.println(uploadedFile);
        twilioService.sendSms("+919301615909","Success Booking");
        sendGridService.sendEmail("shivashandilya8@gmail.com", "shakshiashti2@gmail.com", "Booking status", "Confirm");
        return new ResponseEntity<>("SMS sent successfully",HttpStatus.OK);

    }
    return new ResponseEntity<>("upload ",HttpStatus.OK);

}

    public static MultipartFile convert(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        return new MultipartFile() {
            @Override
            public String getName() {
                return file.getName();
            }

            @Override
            public String getOriginalFilename() {
                return file.getName();
            }

            @Override
            public String getContentType() {
                // You may need to determine the content type based on the file extension
                return "application/octet-stream";
            }

            @Override
            public boolean isEmpty() {
                return file.length() == 0;
            }

            @Override
            public long getSize() {
                return file.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                byte[] bytes = new byte[(int) file.length()];
                int read = fileInputStream.read(bytes);
                if (read != file.length()) {
                    throw new IOException("Failed to read entire file");
                }
                return bytes;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return fileInputStream;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                // You may implement this method if needed
                throw new UnsupportedOperationException("Method not implemented");
            }
        };
    }
}
