package com.autoEuropeTest.autoEurope.model;
import lombok.*;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class Car {

    private Long id;
    private String name;


}