package com.autoEuropeTest.autoEurope.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CarAvailabilityDTO {

    private Long id;
    private CarDTO carDTODAO;
    private LocationDTO locationDTO;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
}
