package com.autoEuropeTest.autoEurope.controller.carAvailability;

import com.autoEuropeTest.autoEurope.model.CarAvailability;
import com.autoEuropeTest.autoEurope.response.CarAvailabilityResponse;
import com.autoEuropeTest.autoEurope.service.carAvailability.CarAvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car/availability")
public class CarAvailabilityControllerImpl implements CarAvailabilityController {

    private final CarAvailabilityService carAvailabilityService;

    public CarAvailabilityControllerImpl(CarAvailabilityService carAvailabilityService) {
        this.carAvailabilityService = carAvailabilityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarAvailabilityResponse> getCarAvailabilityById(@PathVariable("id") Long id) {
        try {
            var carAvailability = carAvailabilityService.getCarAvailabilityById(id);
            if (carAvailability != null) {
                return carAvailability;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            var errorResponse = new CarAvailabilityResponse(null, "Error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/")
    public ResponseEntity<CarAvailabilityResponse> createCarAvailability(@RequestBody CarAvailability carAvailability) {
        try {
            var createdCarAvailability = carAvailabilityService.createCarAvailability(carAvailability);
            return createdCarAvailability;
        } catch (Exception e) {
            var errorResponse = new CarAvailabilityResponse(null, "Error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarAvailabilityResponse> updateCarAvailability(@PathVariable("id") Long id, @RequestBody CarAvailability carAvailability) {
        try {
            var updatedCarAvailability = carAvailabilityService.updateCarAvailability(id, carAvailability);
            if (updatedCarAvailability != null) {
                return updatedCarAvailability;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            var errorResponse = new CarAvailabilityResponse(null, "Error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarAvailabilityResponse> deleteCarAvailability(@PathVariable("id") Long id) {
        try {
            ResponseEntity<CarAvailabilityResponse> response = carAvailabilityService.deleteCarAvailability(id);
            if (response.hasBody()) {
                var responseDeleted = new CarAvailabilityResponse(null, "Car availability deleted successfully");
                return ResponseEntity.ok(responseDeleted);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            var errorResponse = new CarAvailabilityResponse(null, "Error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/")
    public ResponseEntity<CarAvailabilityResponse> getAllCarAvailabilities() {
        try {
            var carAvailabilities = carAvailabilityService.getAllCarAvailabilities();
            return carAvailabilities;
        } catch (Exception e) {
            var errorResponse = new CarAvailabilityResponse(null, "Error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
