package com.autoEuropeTest.autoEurope.service.carAvailability;

import com.autoEuropeTest.autoEurope.model.CarAvailability;
import com.autoEuropeTest.autoEurope.response.CarAvailabilityResponse;
import com.autoEuropeTest.autoEurope.response.CarResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CarAvailabilityService {

    ResponseEntity<CarAvailabilityResponse>  getCarAvailabilityById(Long id);

    ResponseEntity<CarAvailabilityResponse> createCarAvailability(CarAvailability carAvailability);

    ResponseEntity<CarAvailabilityResponse> updateCarAvailability(Long id, CarAvailability carAvailability);

    ResponseEntity<CarAvailabilityResponse>  deleteCarAvailability(Long id);

    ResponseEntity<CarAvailabilityResponse>  getAllCarAvailabilities();
}
