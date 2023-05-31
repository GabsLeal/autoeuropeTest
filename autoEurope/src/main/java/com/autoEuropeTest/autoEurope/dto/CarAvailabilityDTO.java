package com.autoEuropeTest.autoEurope.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class CarAvailabilityDTO {

    private Long id;
    private CarDTO carDTO;
    private LocationDTO locationDTO;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
}