package com.autoEuropeTest.autoEurope.response;

import com.autoEuropeTest.autoEurope.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
public class CarResponse {

    private List<Car> car;
    private String Message;
}