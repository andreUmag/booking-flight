package app.booking.dtos;

public record BookingToSaveDto(Long id,
                               String flightNumber,
                               String passengerName) {
}
