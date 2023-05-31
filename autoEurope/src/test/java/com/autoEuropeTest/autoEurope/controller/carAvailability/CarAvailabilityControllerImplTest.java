package com.autoEuropeTest.autoEurope.controller.carAvailability;

import static org.junit.jupiter.api.Assertions.*;

import com.autoEuropeTest.autoEurope.dao.carAvailability.CarAvailabilityDAO;
import com.autoEuropeTest.autoEurope.dto.CarAvailabilityDTO;
import com.autoEuropeTest.autoEurope.mapper.CarAvailabilityMapper;
import com.autoEuropeTest.autoEurope.model.CarAvailability;
import com.autoEuropeTest.autoEurope.response.CarAvailabilityResponse;
import com.autoEuropeTest.autoEurope.service.carAvailability.CarAvailabilityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarAvailabilityControllerImplTest {

    @Mock
    private CarAvailabilityService carAvailabilityService;

    @InjectMocks
    private CarAvailabilityControllerImpl carAvailabilityController;

    @Mock
    private CarAvailability carAvailability ;

    @Mock
    private CarAvailabilityDTO carAvailabilityDTO;
    @Mock
    private CarAvailabilityDAO carAvailabilityDAO;

    @Mock
    private CarAvailabilityMapper carAvailabilityMapper;


    public CarAvailabilityControllerImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCarAvailabilityById_ValidId_ReturnsCarAvailability() {

        CarAvailability convertedCarAvailability = carAvailabilityMapper.convertToEntity(carAvailabilityDTO);

        CarAvailabilityResponse carAvailabilityResponse = new CarAvailabilityResponse(
                Collections.singletonList(convertedCarAvailability), "CREATED"
        );

        when(carAvailabilityService.getCarAvailabilityById(carAvailability.getId()))
                .thenReturn(ResponseEntity.ok(carAvailabilityResponse));

        ResponseEntity<CarAvailabilityResponse> response =
                carAvailabilityController.getCarAvailabilityById(carAvailability.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carAvailabilityResponse, response.getBody());
        verify(carAvailabilityService, times(1)).getCarAvailabilityById(carAvailability.getId());
    }

    @Test
    public void testGetCarAvailabilityById_InvalidId_ReturnsNotFound() {

        when(carAvailabilityService.getCarAvailabilityById(carAvailability.getId())).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<CarAvailabilityResponse> response = carAvailabilityController.getCarAvailabilityById(carAvailability.getId());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(carAvailabilityService, times(1)).getCarAvailabilityById(carAvailability.getId());
    }

    @Test
    public void testCreateCarAvailability_ValidCarAvailability_ReturnsCreatedCarAvailability() {
        CarAvailability carAvailability = new CarAvailability();

        CarAvailabilityDAO carAvailabilityDAO = carAvailabilityMapper.convertToCarAvailabilityDAO(carAvailability);

        CarAvailabilityResponse carAvailabilityResponse = new CarAvailabilityResponse(
                Collections.<CarAvailability>singletonList(carAvailabilityMapper.convertDAOToCarAvailability((CarAvailabilityDAO) Collections.singletonList(carAvailabilityDAO))), "Car availability created successfully."
        );

        when(carAvailabilityService.createCarAvailability(carAvailability)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(carAvailabilityResponse));

        ResponseEntity<CarAvailabilityResponse> response = carAvailabilityController.createCarAvailability(carAvailability);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(carAvailabilityResponse, response.getBody());
        verify(carAvailabilityService, times(1)).createCarAvailability(carAvailability);
    }


    @Test
    public void testUpdateCarAvailability_ValidCarAvailability_ReturnsUpdatedCarAvailability() {
        Long availabilityId = 1L;
        CarAvailability carAvailability = new CarAvailability();
        carAvailability.setId(availabilityId);
        CarAvailabilityResponse carAvailabilityResponse = new CarAvailabilityResponse(
                Collections.<CarAvailability>singletonList(carAvailabilityMapper.convertDAOToCarAvailability((CarAvailabilityDAO) Collections.singletonList(carAvailabilityDAO))), "Car availability created successfully."
        );
        when(carAvailabilityService.updateCarAvailability(availabilityId, carAvailability)).thenReturn(ResponseEntity.ok(carAvailabilityResponse));

        ResponseEntity<CarAvailabilityResponse> response = carAvailabilityController.updateCarAvailability(availabilityId, carAvailability);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carAvailabilityResponse, response.getBody());
        verify(carAvailabilityService, times(1)).updateCarAvailability(availabilityId, carAvailability);
    }

    @Test
    public void testUpdateCarAvailability_InvalidCarAvailability_ReturnsNotFound() {
        Long availabilityId = 1L;
        CarAvailability carAvailability = new CarAvailability();
        carAvailability.setId(availabilityId);

        when(carAvailabilityService.updateCarAvailability(availabilityId, carAvailability)).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<CarAvailabilityResponse> response = carAvailabilityController.updateCarAvailability(availabilityId, carAvailability);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(carAvailabilityService, times(1)).updateCarAvailability(availabilityId, carAvailability);
    }

    @Test
    public void testDeleteCarAvailability_ExistingCarAvailability_ReturnsOk() {
        Long availabilityId = 1L;
        CarAvailabilityResponse carAvailabilityResponse = new CarAvailabilityResponse(null, "Car availability deleted successfully");
        when(carAvailabilityService.deleteCarAvailability(availabilityId)).thenReturn(ResponseEntity.ok(carAvailabilityResponse));

        ResponseEntity<CarAvailabilityResponse> response = carAvailabilityController.deleteCarAvailability(availabilityId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carAvailabilityResponse, response.getBody());
        verify(carAvailabilityService, times(1)).deleteCarAvailability(availabilityId);
    }

    @Test
    public void testDeleteCarAvailability_NonExistingCarAvailability_ReturnsNotFound() {
        Long availabilityId = 1L;
        CarAvailabilityResponse carAvailabilityResponse = new CarAvailabilityResponse(null, "Car availability not found");
        when(carAvailabilityService.deleteCarAvailability(availabilityId)).thenReturn(ResponseEntity.ok(carAvailabilityResponse));

        ResponseEntity<CarAvailabilityResponse> response = carAvailabilityController.deleteCarAvailability(availabilityId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carAvailabilityResponse, response.getBody());
        verify(carAvailabilityService, times(1)).deleteCarAvailability(availabilityId);
    }

    @Test
    public void testGetAllCarAvailabilities_ReturnsListOfCarAvailabilities() {
        CarAvailabilityDTO carAvailabilityDTO = new CarAvailabilityDTO();
        CarAvailabilityResponse carAvailabilityResponse = new CarAvailabilityResponse(null, "Car availability deleted successfully");

        when(carAvailabilityService.getAllCarAvailabilities()).thenReturn(ResponseEntity.ok(carAvailabilityResponse));

        ResponseEntity<CarAvailabilityResponse> response = carAvailabilityController.getAllCarAvailabilities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carAvailabilityResponse, response.getBody());
        verify(carAvailabilityService, times(1)).getAllCarAvailabilities();
    }
}
