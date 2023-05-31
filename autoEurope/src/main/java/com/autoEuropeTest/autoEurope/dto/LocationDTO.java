package com.autoEuropeTest.autoEurope.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class LocationDTO {

    private Long id;
    private String name;
    private List<CarAvailabilityDTO> availabilities;
}