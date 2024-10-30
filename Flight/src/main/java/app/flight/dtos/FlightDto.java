package app.flight.dtos;

public record FlightDto(Long id, String flightNumber,
                        String origin,
                        String destination,
                        String departureTime ) {
}
