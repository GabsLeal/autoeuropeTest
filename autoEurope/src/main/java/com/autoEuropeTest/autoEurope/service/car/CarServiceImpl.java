package com.autoEuropeTest.autoEurope.service.car;

import com.autoEuropeTest.autoEurope.dao.car.CarDAO;
import com.autoEuropeTest.autoEurope.dao.car.CarRepository;
import com.autoEuropeTest.autoEurope.dto.CarDTO;
import com.autoEuropeTest.autoEurope.mapper.CarMapper;
import com.autoEuropeTest.autoEurope.model.Car;
import com.autoEuropeTest.autoEurope.response.CarResponse;
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
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public ResponseEntity<CarResponse> getCarById(Long id) {
        try {
            return carRepository.findById(id)
                    .map(optionalCarDAO -> {
                        var car = carMapper.convertDAOToCar(optionalCarDAO);
                        return ResponseEntity.ok(new CarResponse(Collections.singletonList(car), "OK"));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CarResponse(null, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<CarResponse> createCar(Car car) {
        try {
            var savedCarDAO = carRepository.save(carMapper.convertToCarDAO(car));
            var createdCar = carMapper.convertDAOToCar(savedCarDAO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CarResponse(Collections.singletonList(createdCar), "Car created successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CarResponse(null, "Failed to create car."));
        }
    }

    @Override
    public ResponseEntity<CarResponse> updateCar(Long id, Car car) {
        try {
            return carRepository.findById(id)
                    .map(existingCarDAO -> {
                        existingCarDAO.setName(car.getName());
                        var updatedCarDAO = carRepository.save(existingCarDAO);
                        var updatedCar = carMapper.convertDAOToCar(updatedCarDAO);
                        return ResponseEntity.ok(new CarResponse(Collections.singletonList(updatedCar), "Car updated successfully."));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CarResponse(null, "Failed to update car: " + e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<CarResponse> deleteCar(Long id) {
        return carRepository.findById(id)
                .map(existingCarDAO -> {
                    carRepository.delete(existingCarDAO);
                    var carResponse = new CarResponse(null, "Car deleted successfully");
                    return ResponseEntity.ok(carResponse);
                })
                .orElseGet(() -> {
                    CarResponse carResponse = new CarResponse(null, "Car not found");
                    return ResponseEntity.ok(carResponse);
                });
    }

    @Override
    public ResponseEntity<CarResponse> getAllCars() {
        try {
            var carDAOList = carRepository.findAll();
            var carDTOList = carMapper.convertToListDtoFromDAO(carDAOList);
            var carList = carMapper.convertToListEntity(carDTOList);
            var carResponse = new CarResponse(carList, "Cars retrieved successfully");
            return ResponseEntity.ok(carResponse);
        } catch (Exception e) {
            CarResponse carResponse = new CarResponse(null, "Error occurred while retrieving cars");
            return ResponseEntity.ok(carResponse);
        }
    }
}
