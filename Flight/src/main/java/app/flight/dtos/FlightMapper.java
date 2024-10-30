package app.flight.dtos;

import app.flight.models.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    FlightDto toDto(Flight flight);
    Flight toEntity(FlightDto flightDto);
    Flight saveDtoToEntity(FlightToSaveDto flightToSaveDto);
}
