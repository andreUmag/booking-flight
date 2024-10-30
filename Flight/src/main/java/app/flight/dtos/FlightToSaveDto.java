package app.flight.dtos;

public record FlightToSaveDto(Long id, String flightNumber,
                              String origin,
                              String destination,
                              String departureTime) {
}
