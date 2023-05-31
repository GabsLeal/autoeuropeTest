package com.autoEuropeTest.autoEurope.controller.car;

import com.autoEuropeTest.autoEurope.model.Car;
import com.autoEuropeTest.autoEurope.response.CarResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public interface CarController {

    @GetMapping("/{id}")
    ResponseEntity<CarResponse> getCarById(@PathVariable("id") Long id);

    @PostMapping("/")
    ResponseEntity<CarResponse> createCar(@RequestBody Car car);

    @PutMapping("/{id}")
    ResponseEntity<CarResponse> updateCar(@PathVariable("id") Long id, @RequestBody Car c);

    @DeleteMapping("/{id}")
    ResponseEntity<CarResponse> deleteCar(@PathVariable("id") Long id);

    @GetMapping("/")
    ResponseEntity<CarResponse> getAllCars();
}
