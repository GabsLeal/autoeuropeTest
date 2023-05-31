package com.autoEuropeTest.autoEurope.mapper;

import com.autoEuropeTest.autoEurope.dao.carAvailability.CarAvailabilityDAO;
import com.autoEuropeTest.autoEurope.dto.CarAvailabilityDTO;
import com.autoEuropeTest.autoEurope.model.CarAvailability;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarAvailabilityMapper {
    private final ModelMapper modelMapper;

    public CarAvailabilityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CarAvailabilityDTO convertToDto(CarAvailability carAvailability) {
        return modelMapper.map(carAvailability, CarAvailabilityDTO.class);
    }

    public CarAvailability convertToEntity(CarAvailabilityDTO carAvailabilityDTO) {
        return modelMapper.map(carAvailabilityDTO, CarAvailability.class);
    }

    public List<CarAvailabilityDTO> convertToListDto(List<CarAvailability> carAvailabilityList) {
        return carAvailabilityList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CarAvailability> convertToListEntity(List<CarAvailabilityDAO> carAvailabilityDTOList) {
        return carAvailabilityDTOList.stream()
                .map(this::convertDAOToCarAvailability)
                .collect(Collectors.toList());
    }

    public CarAvailability convertDAOToCarAvailability(CarAvailabilityDAO carAvailabilityDAO) {
        return modelMapper.map(carAvailabilityDAO, CarAvailability.class);

    }

    public CarAvailabilityDAO convertToCarAvailabilityDAO(CarAvailability carAvailability) {
        return modelMapper.map(carAvailability, CarAvailabilityDAO.class);
    }

    public List<CarAvailability> convertToListDtoFromDAO(List<CarAvailabilityDAO> carAvailabilityDAOS) {
        return carAvailabilityDAOS.stream()
                .map(this::convertDAOToCarAvailability)
                .collect(Collectors.toList());
    }

}
