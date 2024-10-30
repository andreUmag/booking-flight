package app.booking.services;

import app.booking.dtos.BookingDto;
import app.booking.dtos.BookingToSaveDto;

import java.util.List;
import java.util.Optional;


public interface BookingService {
    BookingDto saveBooking(BookingToSaveDto bookingToSaveDto);
    List<BookingDto> getAllBookings();
    Optional<BookingDto> getBookingById(Long id);
    BookingDto updateBooking(Long id, BookingToSaveDto bookingToSaveDto);
    void deleteBooking(Long id);
}
