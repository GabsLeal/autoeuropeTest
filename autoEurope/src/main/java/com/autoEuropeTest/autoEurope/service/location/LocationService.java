package com.autoEuropeTest.autoEurope.service.location;

import com.autoEuropeTest.autoEurope.dto.LocationDTO;
import com.autoEuropeTest.autoEurope.model.Location;
import com.autoEuropeTest.autoEurope.response.LocationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LocationService {

    ResponseEntity<LocationResponse> getLocationById(Long id);
    ResponseEntity<LocationResponse> createLocation(Location location);
    ResponseEntity<LocationResponse> updateLocation(Long id, Location location);
    ResponseEntity<LocationResponse> deleteLocation(Long id);
    ResponseEntity<LocationResponse> getAllLocations();
}
