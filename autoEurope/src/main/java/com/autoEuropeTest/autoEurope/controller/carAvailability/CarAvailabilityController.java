package com.autoEuropeTest.autoEurope.controller.carAvailability;

import com.autoEuropeTest.autoEurope.model.CarAvailability;
import com.autoEuropeTest.autoEurope.response.CarAvailabilityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carAvailability")
public interface CarAvailabilityController {

    @GetMapping("/{id}")
    ResponseEntity<CarAvailabilityResponse> getCarAvailabilityById(@PathVariable("id") Long id);

    @PostMapping("/")
    ResponseEntity<CarAvailabilityResponse> createCarAvailability(@RequestBody CarAvailability carAvailability);

    @PutMapping("/{id}")
    ResponseEntity<CarAvailabilityResponse> updateCarAvailability(@PathVariable("id") Long id, @RequestBody CarAvailability carAvailability);

    @DeleteMapping("/{id}")
    ResponseEntity<CarAvailabilityResponse> deleteCarAvailability(@PathVariable("id") Long id);

    @GetMapping("/")
    ResponseEntity<CarAvailabilityResponse> getAllCarAvailabilities();

}
