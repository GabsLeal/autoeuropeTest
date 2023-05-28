package com.autoEuropeTest.autoEurope.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    private Long id;
    private String name;
    private List<CarAvailabilityDTO> availabilities;

}