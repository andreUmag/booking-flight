package app.flight.services;

import app.flight.dtos.FlightDto;
import app.flight.dtos.FlightToSaveDto;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    FlightDto saveFlight(FlightToSaveDto flightToSaveDto);
    List<FlightDto> getAllFlights();
    Optional<FlightDto> getFlightById(Long id);
    FlightDto updateFlight(Long id, FlightToSaveDto flightToSaveDto);
    void deleteFlight(Long id);
}
