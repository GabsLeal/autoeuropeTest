package com.autoEuropeTest.autoEurope.response;

import com.autoEuropeTest.autoEurope.model.Car;
import com.autoEuropeTest.autoEurope.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Data
@AllArgsConstructor
public class LocationResponse {
    private List<Location> locationResponse;
    private String Message;
}