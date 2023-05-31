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

@RequiredArgsConstructor
@RestController
@RequestMapping("/car")
public class CarControllerImpl implements CarController {

    private final CarService carService;
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CarResponse> getCarById(@PathVariable("id") Long id) {
        try {
            ResponseEntity<CarResponse> car = carService.getCarById(id);
            if (car.getBody() != null) {
                return car;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            CarResponse errorResponse = new CarResponse(null, "An error occurred while retrieving the car.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/")
    @Override
    public ResponseEntity<CarResponse> createCar(@RequestBody Car car) {
        try {
            ResponseEntity<CarResponse> createdCar = carService.createCar(car);
            if (createdCar.getBody() != null) {
                return createdCar;
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
            ResponseEntity<CarResponse> updatedCar = carService.updateCar(id, car);
            if (updatedCar.getBody() != null) {
                return updatedCar;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            CarResponse errorResponse = new CarResponse(null, "An error occurred while updating the car.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<CarResponse> deleteCar(@PathVariable("id") Long id) {
        try {
            ResponseEntity<CarResponse> deleted = carService.deleteCar(id);
            if (deleted.getBody() != null && deleted.getBody().getCar() != null) {
                return deleted;
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            CarResponse errorResponse = new CarResponse(null, "An error occurred while deleting the car.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @GetMapping("/")
    @Override
    public ResponseEntity<CarResponse> getAllCars() {
        try {
            ResponseEntity<CarResponse> cars = carService.getAllCars();

            if (cars.getBody() != null) {
                List<Car> carList = cars.getBody().getCar();
                CarResponse carResponse = new CarResponse(carList, "Success");
                return ResponseEntity.ok(carResponse);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            CarResponse carResponse = new CarResponse(null, "Error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(carResponse);
        }
    }
}
