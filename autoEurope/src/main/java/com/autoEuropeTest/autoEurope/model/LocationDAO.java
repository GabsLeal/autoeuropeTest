package com.autoEuropeTest.autoEurope.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LocationDAO {

    private Long id;
    private String name;
    private List<CarAvailability> availabilities;

}