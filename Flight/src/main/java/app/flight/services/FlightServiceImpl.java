package app.flight.services;

import app.flight.dtos.FlightDto;
import app.flight.dtos.FlightMapper;
import app.flight.dtos.FlightToSaveDto;
import app.flight.models.Flight;
import app.flight.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService{

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }


    @Override
    public FlightDto saveFlight(FlightToSaveDto flightToSaveDto) {
        Flight flight = flightMapper.saveDtoToEntity(flightToSaveDto);
        Flight flightSaved = flightRepository.save(flight);
        return flightMapper.toDto(flightSaved);
    }

    @Override
    public List<FlightDto> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights.stream().map(flight -> flightMapper.toDto(flight)).toList();
    }

    @Override
    public Optional<FlightDto> getFlightById(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        return flight.map(flightMapper::toDto);
    }

    @Override
    public FlightDto updateFlight(Long id, FlightToSaveDto flightToSaveDto) {
        Optional<Flight> existingFlight = flightRepository.findById(id);

        if (existingFlight.isPresent()) {
            Flight flight = existingFlight.get();
            flight.setFlightNumber(flightToSaveDto.flightNumber());
            flight.setOrigin(flightToSaveDto.origin());
            flight.setDestination(flightToSaveDto.destination());
            flight.setDepartureTime(flightToSaveDto.departureTime());
            Flight updatedFlight = flightRepository.save(flight);
            return flightMapper.toDto(updatedFlight);
        }

        throw new RuntimeException("Flight no encontrado con el id" + id);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

}
