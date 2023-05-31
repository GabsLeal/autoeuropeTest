package com.autoEuropeTest.autoEurope.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class Location {

    private Long id;
    private String name;
    private List<CarAvailability> availabilities;

}