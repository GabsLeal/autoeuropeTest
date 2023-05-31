package com.autoEuropeTest.autoEurope.mapper;

import com.autoEuropeTest.autoEurope.dao.location.LocationDAO;
import com.autoEuropeTest.autoEurope.dto.LocationDTO;
import com.autoEuropeTest.autoEurope.model.Location;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationMapper {
    private final ModelMapper modelMapper;

    public LocationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LocationDTO convertToDto(LocationDAO locationDAO) {
        return modelMapper.map(locationDAO, LocationDTO.class);
    }

    public Location convertToEntity(LocationDTO locationDTO) {
        return modelMapper.map(locationDTO, Location.class);
    }

    public List<LocationDTO> convertToListDto(List<LocationDAO> locationDAOList) {
        return locationDAOList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<Location> convertToListEntity(List<LocationDTO> locationDTOList) {
        return locationDTOList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }


    public Location convertDAOToLocation(LocationDAO locationDAO) {
        return modelMapper.map(locationDAO, Location.class);
    }

    public LocationDAO convertToLocationDAO(Location location) {
        return modelMapper.map(location, LocationDAO.class);
    }
}
