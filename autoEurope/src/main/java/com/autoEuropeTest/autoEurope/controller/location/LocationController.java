package com.autoEuropeTest.autoEurope.controller.location;

import com.autoEuropeTest.autoEurope.model.Location;
import com.autoEuropeTest.autoEurope.response.LocationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public interface LocationController {

    @GetMapping("/{id}")
    ResponseEntity<LocationResponse> getLocationById(@PathVariable("id") Long id);

    @PostMapping("/")
    ResponseEntity<LocationResponse> createLocation(@RequestBody Location location);

    @PutMapping("/{id}")
    ResponseEntity<LocationResponse> updateLocation(@PathVariable("id") Long id, @RequestBody Location location);

    @DeleteMapping("/{id}")
    ResponseEntity<LocationResponse> deleteLocation(@PathVariable("id") Long id);

    @GetMapping("/")
    ResponseEntity<LocationResponse> getAllLocations();
}
