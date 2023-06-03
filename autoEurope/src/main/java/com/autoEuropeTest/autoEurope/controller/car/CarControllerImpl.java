package com.autoEuropeTest.autoEurope.controller.car;

import com.autoEuropeTest.autoEurope.model.Car;
import com.autoEuropeTest.autoEurope.response.CarResponse;
import com.autoEuropeTest.autoEurope.service.car.CarService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarControllerImpl implements CarController {

    private final CarService carService;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CarResponse> getCarById(@PathVariable("id") Long id) {
        try {
            var response = carService.getCarById(id);
            if (response.getBody() != null && response.getBody().getCar() != null) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            var errorResponse = new CarResponse(null, "An error occurred while retrieving the car.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/")
    @Override
    public ResponseEntity<CarResponse> createCar(@RequestBody Car car) {
        try {
            var response = carService.createCar(car);
            if (response.getBody() != null && response.getBody().getCar() != null) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            CarResponse errorResponse = new CarResponse(null, "An error occurred while creating the car.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<CarResponse> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        try {
            var response = carService.updateCar(id, car);
            if (response.getBody() != null && response.getBody().getCar() != null) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            var errorResponse = new CarResponse(null, "An error occurred while updating the car.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<CarResponse> deleteCar(@PathVariable("id") Long id) {
        try {
            var response = carService.deleteCar(id);
            if (response.getBody() != null && response.getBody().getCar() != null) {
                return response;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            var errorResponse = new CarResponse(null, "An error occurred while deleting the car.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/")
    @Override
    public ResponseEntity<CarResponse> getAllCars() {
        try {
            var response = carService.getAllCars();
            if (response.getBody() != null && response.getBody().getCar() != null) {
                var carList = response.getBody().getCar();
                CarResponse carResponse = new CarResponse(carList, "Success");
                return ResponseEntity.ok(carResponse);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            var carResponse = new CarResponse(null, "Error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(carResponse);
        }
    }


}
