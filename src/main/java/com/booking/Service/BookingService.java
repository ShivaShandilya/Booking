package com.booking.Service;

import com.booking.Dto.BookingDto;
import com.booking.Repository.BookingRepository;
import com.booking.Repository.PropertyRepository;
import com.booking.entity.Booking;
import com.booking.entity.Property;
import com.booking.entity.PropertyUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

   private PropertyRepository propertyRepository;
    private final BookingRepository bookingRepository;

    public BookingService(PropertyRepository propertyRepository,
                          BookingRepository bookingRepository) {
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
    }

    public Booking cerateBooking(BookingDto bookingDto, long propertyId, PropertyUser user) {
        int totalNight = bookingDto.getTotalNight();

        Property property =new Property();
        Booking booking= new Booking();


        Property finalproperty = propertyRepository.findById(propertyId).get();
        Integer nightlyPrice = finalproperty.getNightlyPrice();
        Integer totalPrice=totalNight * nightlyPrice ;
        double gst = totalPrice * 0.18;
        booking.setGstIncludeBill(gst);

        booking.setGuestName(bookingDto.getGuestName());
        booking.setTotalNight(bookingDto.getTotalNight());
        booking.setTotalPrice(totalPrice);
        booking.setProperty(finalproperty);
        booking.setPropertyUser(user);
        Booking save = bookingRepository.save(booking);
        return save;


    }
}
