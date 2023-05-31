package com.autoEuropeTest.autoEurope.service.carAvailability;

import com.autoEuropeTest.autoEurope.dao.car.CarDAO;
import com.autoEuropeTest.autoEurope.dao.carAvailability.CarAvailabilityDAO;
import com.autoEuropeTest.autoEurope.dao.carAvailability.CarAvailabilityRepository;
import com.autoEuropeTest.autoEurope.mapper.CarAvailabilityMapper;
import com.autoEuropeTest.autoEurope.model.CarAvailability;
import com.autoEuropeTest.autoEurope.response.CarAvailabilityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CarAvailabilityServiceImpl implements CarAvailabilityService {

    private final CarAvailabilityRepository carAvailabilityRepository;
    private final CarAvailabilityMapper carAvailabilityMapper;

    public CarAvailabilityServiceImpl(CarAvailabilityRepository carAvailabilityRepository, CarAvailabilityMapper carAvailabilityMapper) {
        this.carAvailabilityRepository = carAvailabilityRepository;
        this.carAvailabilityMapper = carAvailabilityMapper;
    }

    @Override
    public ResponseEntity<CarAvailabilityResponse> getCarAvailabilityById(Long id) {
        try {
            Optional<CarAvailabilityDAO> optionalCarAvailabilityDAO = carAvailabilityRepository.findById(id);
            if (optionalCarAvailabilityDAO.isPresent()) {
                CarAvailability carAvailability = carAvailabilityMapper.convertDAOToCarAvailability(optionalCarAvailabilityDAO.get());
                return ResponseEntity.ok(new CarAvailabilityResponse(Collections.singletonList(carAvailability), "OK"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            CarAvailabilityResponse errorResponse = new CarAvailabilityResponse(null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Override
    public ResponseEntity<CarAvailabilityResponse> createCarAvailability(CarAvailability carAvailability) {
        try {
            CarAvailabilityDAO savedCarAvailabilityDAO = carAvailabilityRepository.save(carAvailabilityMapper.convertToCarAvailabilityDAO(carAvailability));
            CarAvailability createdCarAvailability = carAvailabilityMapper.convertDAOToCarAvailability(savedCarAvailabilityDAO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CarAvailabilityResponse(Collections.singletonList(createdCarAvailability), "Car availability created successfully."));
        } catch (Exception e) {
            CarAvailabilityResponse errorResponse = new CarAvailabilityResponse(null, "Failed to create car availability.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Override
    public ResponseEntity<CarAvailabilityResponse> updateCarAvailability(Long id, CarAvailability carAvailability) {
        try {
            Optional<CarAvailabilityDAO> optionalCarAvailabilityDAO = carAvailabilityRepository.findById(id);
            if (optionalCarAvailabilityDAO.isPresent()) {
                CarAvailabilityDAO existingCarAvailabilityDAO = optionalCarAvailabilityDAO.get();

                CarAvailabilityDAO updatedCarAvailabilityDAO = carAvailabilityRepository.save(existingCarAvailabilityDAO);
                CarAvailability updatedCarAvailability = carAvailabilityMapper.convertDAOToCarAvailability(updatedCarAvailabilityDAO);
                return ResponseEntity.ok(new CarAvailabilityResponse(Collections.singletonList(updatedCarAvailability), "Car availability updated successfully."));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            CarAvailabilityResponse errorResponse = new CarAvailabilityResponse(null, "Failed to update car availability: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Override
    public ResponseEntity<CarAvailabilityResponse> deleteCarAvailability(Long id) {
        try {
            Optional<CarAvailabilityDAO> optionalCarAvailabilityDAO = carAvailabilityRepository.findById(id);
            if (optionalCarAvailabilityDAO.isPresent()) {
                carAvailabilityRepository.delete(optionalCarAvailabilityDAO.get());
                CarAvailabilityResponse response = new CarAvailabilityResponse(null, "Car availability deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            CarAvailabilityResponse errorResponse = new CarAvailabilityResponse(null, "Error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Override
    public ResponseEntity<CarAvailabilityResponse> getAllCarAvailabilities() {
        try {
            List<CarAvailabilityDAO> carAvailabilityDAOList = carAvailabilityRepository.findAll();
            List<CarAvailability> carAvailabilityList = carAvailabilityMapper.convertToListEntity(carAvailabilityDAOList);
            CarAvailabilityResponse response = new CarAvailabilityResponse(carAvailabilityList, "Success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            CarAvailabilityResponse errorResponse = new CarAvailabilityResponse(null, "Error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
