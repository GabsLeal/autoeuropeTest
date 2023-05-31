package com.autoEuropeTest.autoEurope.service.car;

import com.autoEuropeTest.autoEurope.model.Car;
import com.autoEuropeTest.autoEurope.response.CarResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    ResponseEntity<CarResponse> getCarById(Long id);

    ResponseEntity<CarResponse> createCar(Car car);

    ResponseEntity<CarResponse> updateCar(Long id, Car car);

    ResponseEntity<CarResponse> deleteCar(Long id);

    ResponseEntity<CarResponse> getAllCars();
}
