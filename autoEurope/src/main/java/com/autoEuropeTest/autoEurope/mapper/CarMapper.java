package com.autoEuropeTest.autoEurope.mapper;

import com.autoEuropeTest.autoEurope.dao.car.CarDAO;
import com.autoEuropeTest.autoEurope.dto.CarDTO;
import com.autoEuropeTest.autoEurope.model.Car;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CarMapper {
    private final ModelMapper modelMapper;

    public CarMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CarDTO convertToDto(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }

    public Car convertToEntity(CarDTO carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }

    public CarDAO convertToDAO(Car car) {
        return modelMapper.map(car, CarDAO.class);
    }

    public List<CarDTO> convertToListDto(List<Car> carList) {
        return carList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<Car> convertToListEntity(List<CarDTO> carDTOList) {
        return carDTOList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public Car convertDAOToCar(CarDAO carDAO) {
        return modelMapper.map(carDAO, Car.class);
    }

    public CarDAO convertToCarDAO(Car car) {
        return modelMapper.map(car, CarDAO.class);
    }

    public List<CarDTO> convertToListDtoFromDAO(List<CarDAO> carDAOList) {
        return carDAOList.stream()
                .map(this::convertToDtoFromDAO)
                .collect(Collectors.toList());
    }

    public CarDTO convertToDtoFromDAO(CarDAO carDAO) {
        return modelMapper.map(carDAO, CarDTO.class);
    }
}
