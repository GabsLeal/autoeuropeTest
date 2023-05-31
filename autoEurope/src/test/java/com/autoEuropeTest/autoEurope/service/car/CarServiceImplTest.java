package com.autoEuropeTest.autoEurope.service.car;

import com.autoEuropeTest.autoEurope.dao.car.CarDAO;
import com.autoEuropeTest.autoEurope.dao.car.CarRepository;
import com.autoEuropeTest.autoEurope.dto.CarDTO;
import com.autoEuropeTest.autoEurope.mapper.CarMapper;
import com.autoEuropeTest.autoEurope.model.Car;
import com.autoEuropeTest.autoEurope.response.CarResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void getCarById_ExistingCar_ReturnsCar() {
        // Arrange
        Long carId = 1L;
        CarDAO carDAO = new CarDAO();
        Car car = new Car();

        when(carRepository.findById(carId)).thenReturn(Optional.of(carDAO));
        when(carMapper.convertDAOToCar(carDAO)).thenReturn(car);

        // Act
        ResponseEntity<CarResponse> response = carService.getCarById(carId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car, response.getBody());
    }

    @Test
    void getCarById_NonExistingCar_ReturnsNotFound() {
        // Arrange
        Long carId = 1L;

        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<CarResponse> response = carService.getCarById(carId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createCar_ValidCar_ReturnsCreatedCar() {
        // Arrange
        Car car = new Car();
        CarDAO savedCarDAO = new CarDAO();
        Car createdCar = new Car();

        when(carMapper.convertToCarDAO(car)).thenReturn(savedCarDAO);
        when(carRepository.save(savedCarDAO)).thenReturn(savedCarDAO);
        when(carMapper.convertDAOToCar(savedCarDAO)).thenReturn(createdCar);

        // Act
        ResponseEntity<CarResponse>response = carService.createCar(car);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdCar, response.getBody());
    }

    @Test
    void updateCar_ExistingCar_ReturnsUpdatedCar() {
        // Arrange
        Long carId = 1L;
        Car car = new Car();
        CarDAO existingCarDAO = new CarDAO();
        CarDAO updatedCarDAO = new CarDAO();
        Car updatedCar = new Car();

        when(carRepository.findById(carId)).thenReturn(Optional.of(existingCarDAO));
        when(carRepository.save(existingCarDAO)).thenReturn(updatedCarDAO);
        when(carMapper.convertDAOToCar(updatedCarDAO)).thenReturn(updatedCar);

        // Act
        ResponseEntity<CarResponse> response = carService.updateCar(carId, car);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCar, response.getBody());
    }

    @Test
    void updateCar_NonExistingCar_ReturnsNotFound() {
        // Arrange
        Long carId = 1L;
        Car car = new Car();

        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<CarResponse> response = carService.updateCar(carId, car);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteCar_ExistingCar_ReturnsTrue() {
        // Arrange
        Long carId = 1L;
        CarDAO carDAO = new CarDAO();

        when(carRepository.findById(carId)).thenReturn(Optional.of(carDAO));

        // Act
        ResponseEntity<CarResponse> response = carService.deleteCar(carId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
    }

    @Test
    void deleteCar_NonExistingCar_ReturnsFalse() {
        // Arrange
        Long carId = 1L;

        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<CarResponse> response = carService.deleteCar(carId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    void getAllCars_ReturnsListOfCars() {
        // Arrange
        List<CarDAO> carDAOList = new ArrayList<>();
        carDAOList.add(new CarDAO());
        carDAOList.add(new CarDAO());

        List<CarDTO> carDTOList = new ArrayList<>();
        carDTOList.add(new CarDTO());
        carDTOList.add(new CarDTO());

        List<Car> carList = new ArrayList<>();
        carList.add(new Car());
        carList.add(new Car());

        when(carRepository.findAll()).thenReturn(carDAOList);
        when(carMapper.convertToListDtoFromDAO(carDAOList)).thenReturn(carDTOList);
        when(carMapper.convertToListEntity(carDTOList)).thenReturn(carList);

        // Act
        ResponseEntity<CarResponse> response = carService.getAllCars();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carList, response.getBody());
    }
}