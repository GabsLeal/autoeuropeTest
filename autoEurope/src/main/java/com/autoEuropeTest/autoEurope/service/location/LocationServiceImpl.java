package com.autoEuropeTest.autoEurope.service.location;

import com.autoEuropeTest.autoEurope.dao.car.CarDAO;
import com.autoEuropeTest.autoEurope.dao.location.LocationDAO;
import com.autoEuropeTest.autoEurope.dao.location.LocationRepository;
import com.autoEuropeTest.autoEurope.dto.LocationDTO;
import com.autoEuropeTest.autoEurope.mapper.LocationMapper;
import com.autoEuropeTest.autoEurope.model.Location;
import com.autoEuropeTest.autoEurope.response.LocationResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationMapper locationMapper;

    private final LocationRepository locationRepository;

    @Override
    public ResponseEntity<LocationResponse> getLocationById(Long id) {
        try {
            Optional<LocationDAO> locationDAO = locationRepository.findById(id);
            if (locationDAO.isPresent()) {
                Location location = locationMapper.convertDAOToLocation(locationDAO.get());
                return ResponseEntity.ok(new LocationResponse(Collections.singletonList(location), "OK"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LocationResponse(null, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<LocationResponse> createLocation(Location location) {
        try {
            LocationDAO savedLocationDAO = locationRepository.save(locationMapper.convertToLocationDAO(location));
            Location createdLocation = locationMapper.convertDAOToLocation(savedLocationDAO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new LocationResponse(Collections.singletonList(createdLocation), "Car created successfully."));
            }
         catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LocationResponse(null, "Failed to create location."));
        }
    }

    @Override
    public ResponseEntity<LocationResponse> updateLocation(Long id, Location location) {
        try {
            Optional<LocationDAO> optionalLocationDAO  = locationRepository.findById(id);
            if (optionalLocationDAO.isPresent()) {
                LocationDAO existingLocationDAO = optionalLocationDAO.get();
                existingLocationDAO.setName(location.getName());

                LocationDAO updatedLocationDAO = locationRepository.save(existingLocationDAO);
                Location updatedLocation = locationMapper.convertDAOToLocation(updatedLocationDAO);
                return ResponseEntity.ok(new LocationResponse(Collections.singletonList(updatedLocation), "Location updated successfully."));

            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LocationResponse(null, "Failed to update location: " + e.getMessage()));        }
    }

    @Override
    public ResponseEntity<LocationResponse> deleteLocation(Long id) {
        try {
            Optional<LocationDAO> optionalLocationAO = locationRepository.findById(id);

            if (optionalLocationAO.isPresent()) {
                locationRepository.delete(optionalLocationAO.get());
                LocationResponse locationResponse = new LocationResponse(null, "Location deleted successfully");
                return ResponseEntity.ok(locationResponse);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            LocationResponse errorResponse = new LocationResponse(null, "An error occurred while deleting the location.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Override
    public ResponseEntity<LocationResponse> getAllLocations() {
        try {
            List<LocationDAO> locations = locationRepository.findAll();
            List<LocationDTO> locationDTOs = locationMapper.convertToListDto(locations);
            List<Location> locationList = locationMapper.convertToListEntity(locationDTOs);

            LocationResponse locationResponse = new LocationResponse(locationList, "Locations found");
            return ResponseEntity.ok(locationResponse);
        } catch (Exception e) {
            LocationResponse errorResponse = new LocationResponse(null, "An error occurred while retrieving the locations.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
