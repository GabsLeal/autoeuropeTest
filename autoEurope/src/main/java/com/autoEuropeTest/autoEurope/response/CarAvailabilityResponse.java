package com.autoEuropeTest.autoEurope.response;

import com.autoEuropeTest.autoEurope.model.Car;
import com.autoEuropeTest.autoEurope.model.CarAvailability;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Data
@AllArgsConstructor
public class CarAvailabilityResponse {

    private List<CarAvailability> carAvailability;

    private String Message;
}