package com.autoEuropeTest.autoEurope.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CarAvailability {

    private Long id;
    private Car carDAO;
    private LocationDAO locationDAO;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
}
