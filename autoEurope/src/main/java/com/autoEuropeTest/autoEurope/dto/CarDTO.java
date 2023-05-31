package com.autoEuropeTest.autoEurope.dto;
import lombok.*;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class CarDTO {

    private Long id;
    private String name;
}