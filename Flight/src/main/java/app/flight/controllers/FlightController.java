package app.flight.controllers;
import app.flight.dtos.FlightDto;
import app.flight.dtos.FlightToSaveDto;
import app.flight.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flights")
@CrossOrigin(value = "http://localhost:5173")
public class FlightController {
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<FlightDto>  saveUser(@RequestBody FlightToSaveDto flightToSaveDto) {
        FlightDto savedFlight = flightService.saveFlight(flightToSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
    }

    @GetMapping()
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<FlightDto> flightDtoList = flightService.getAllFlights();
        return  ResponseEntity.ok(flightDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) {
        Optional<FlightDto> flightDto = flightService.getFlightById(id);
        return flightDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Long id, @RequestBody FlightToSaveDto flightToSaveDto) {
        try {
            FlightDto updatedFlight = flightService.updateFlight(id, flightToSaveDto);
            return ResponseEntity.ok(updatedFlight);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
