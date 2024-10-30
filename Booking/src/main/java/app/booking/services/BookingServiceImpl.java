package app.booking.services;

import app.booking.dtos.BookingDto;
import app.booking.dtos.BookingMapper;
import app.booking.dtos.BookingToSaveDto;
import app.booking.models.Booking;
import app.booking.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public BookingDto saveBooking(BookingToSaveDto bookingToSaveDto) {
        Booking booking = bookingMapper.saveDtoToEntity(bookingToSaveDto);
        Booking bookingSaved = bookingRepository.save(booking);
        return bookingMapper.toDto(bookingSaved);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(bookingMapper::toDto).toList();
    }

    @Override
    public Optional<BookingDto> getBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(bookingMapper::toDto);
    }

    @Override
    public BookingDto updateBooking(Long id, BookingToSaveDto bookingToSaveDto) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);

        if (existingBooking.isPresent()) {
            Booking booking = existingBooking.get();
            booking.setPassengerName(bookingToSaveDto.passengerName());
            booking.setFlightNumber(bookingToSaveDto.flightNumber());
            Booking updatedBooking = bookingRepository.save(booking);
            return bookingMapper.toDto(updatedBooking);
        }

        throw new RuntimeException("Booking no encontrado con id: " + id);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
