package com.autoEuropeTest.autoEurope.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class CarAvailability {

    private Long id;
    private Car car;
    private Location location;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
}
