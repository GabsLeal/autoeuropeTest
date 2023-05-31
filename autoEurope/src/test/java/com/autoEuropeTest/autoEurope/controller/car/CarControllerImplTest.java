package com.autoEuropeTest.autoEurope.controller.car;

import static org.junit.jupiter.api.Assertions.*;

import com.autoEuropeTest.autoEurope.model.Car;
import com.autoEuropeTest.autoEurope.response.CarResponse;
import com.autoEuropeTest.autoEurope.service.car.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerImplTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarControllerImpl carController;

    @Test
    public void testGetCarById_ValidId_ReturnsCar() {
        Long carId = 1L;
        Car car = new Car(carId, "Test Car");
        CarResponse carResponse = new CarResponse(Collections.singletonList(car), "CREATED");

        when(carService.getCarById(carId)).thenReturn(ResponseEntity.ok(carResponse));

        ResponseEntity<CarResponse> response = carController.getCarById(carId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carResponse, response.getBody());
        verify(carService, times(1)).getCarById(carId);
    }

    @Test
    public void testGetCarById_InvalidId_ReturnsNotFound() {
        Long carId = 1L;

        when(carService.getCarById(carId)).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<CarResponse> response = carController.getCarById(carId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(carService, times(1)).getCarById(carId);
    }

    @Test
    public void testCreateCar_ValidCar_ReturnsCreatedCar() {
        Car car = new Car(1L, "Test Car");

        when(carService.createCar(car)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(new CarResponse(Collections.singletonList(car), "Car created successfully.")));

        ResponseEntity<CarResponse> response = carController.createCar(car);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(car, response.getBody());
        verify(carService, times(1)).createCar(car);
    }

    @Test
    public void testUpdateCar_ValidCar_ReturnsUpdatedCar() {
        Long carId = 1L;
        Car car = new Car(carId, "Test Car");
        Car updatedCar = new Car(carId, "Updated Car");

        when(carService.updateCar(carId, car)).thenReturn(ResponseEntity.ok((CarResponse) new CarResponse(Collections.singletonList(updatedCar), "Car updated successfully.")));

        ResponseEntity<CarResponse> response = carController.updateCar(carId, car);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCar, response.getBody());
        verify(carService, times(1)).updateCar(carId, car);
    }

    @Test
    public void testUpdateCar_InvalidCar_ReturnsNotFound() {
        Long carId = 1L;
        Car car = new Car(carId, "Test Car");

        when(carService.updateCar(carId, car)).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<CarResponse> response = carController.updateCar(carId, car);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(carService, times(1)).updateCar(carId, car);
    }

    @Test
    public void testDeleteCar_ExistingCar_ReturnsNoContent() {
        Long carId = 1L;

        CarResponse carResponse = new CarResponse(null, "Car deleted successfully");
        when(carService.deleteCar(carId)).thenReturn(ResponseEntity.ok(carResponse));

        ResponseEntity<CarResponse> response = carController.deleteCar(carId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carResponse, response.getBody());
        verify(carService, times(1)).deleteCar(carId);
    }

    @Test
    public void testDeleteCar_NonExistingCar_ReturnsNotFound() {
        Long carId = 1L;

        CarResponse carResponse = new CarResponse(null, "Car not found");
        when(carService.deleteCar(carId)).thenReturn(ResponseEntity.ok(carResponse));

        ResponseEntity<CarResponse> response = carController.deleteCar(carId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carResponse, response.getBody());
        verify(carService, times(1)).deleteCar(carId);
    }

    @Test
    public void testGetAllCars_ReturnsListOfCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1L, "Car 1"));
        cars.add(new Car(2L, "Car 2"));

        CarResponse carResponse = new CarResponse(cars, "Cars found");
        ResponseEntity<CarResponse> expectedResponse = ResponseEntity.ok(carResponse);
        when(carService.getAllCars()).thenReturn(expectedResponse);

        ResponseEntity<CarResponse> response = carController.getAllCars();

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
        verify(carService, times(1)).getAllCars();
    }

}
