package com.autoEuropeTest.autoEurope.controller.location;


import com.autoEuropeTest.autoEurope.dto.LocationDTO;
import com.autoEuropeTest.autoEurope.model.Location;
import com.autoEuropeTest.autoEurope.response.CarResponse;
import com.autoEuropeTest.autoEurope.response.LocationResponse;
import com.autoEuropeTest.autoEurope.service.location.LocationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationControllerImpl implements LocationController {

    private final LocationService locationService;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<LocationResponse> getLocationById(@PathVariable("id") Long id) {
        try {
            var responseEntity = locationService.getLocationById(id);
            if (responseEntity.getBody() != null) {
                return responseEntity;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            var errorResponse = new LocationResponse(null, "An error occurred while retrieving the location.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/")
    @Override
    public ResponseEntity<LocationResponse> createLocation(@RequestBody Location location) {
        try {
            var responseEntity = locationService.createLocation(location);
            if (responseEntity.getBody() != null) {
                return responseEntity;
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            var errorResponse = new LocationResponse(null, "An error occurred while creating the location.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<LocationResponse> updateLocation(@PathVariable("id") Long id, @RequestBody Location location) {
        try {
            var responseEntity = locationService.updateLocation(id, location);
            if (responseEntity.getBody() != null) {
                return responseEntity;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            var errorResponse = new LocationResponse(null, "An error occurred while updating the location.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<LocationResponse> deleteLocation(@PathVariable("id") Long id) {
        try {
            var responseEntity = locationService.deleteLocation(id);
            if (responseEntity.getBody() != null && responseEntity.getBody().getLocationResponse() != null) {
                return responseEntity;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            var errorResponse = new LocationResponse(null, "An error occurred while deleting the location.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/")
    @Override
    public ResponseEntity<LocationResponse> getAllLocations() {
        try {
            var responseEntity = locationService.getAllLocations();
            if (responseEntity.getBody() != null) {
                var locationList = responseEntity.getBody().getLocationResponse();
                var locationResponse = new LocationResponse(locationList, "Success");

                return ResponseEntity.ok(locationResponse);
            }
        } catch (Exception e) {
            var errorResponse = new LocationResponse(null, "An error occurred while retrieving the locations.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

        return ResponseEntity.notFound().build();
    }
}