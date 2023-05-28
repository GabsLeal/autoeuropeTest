package com.autoEuropeTest.autoEurope.dao.location;

import com.autoEuropeTest.autoEurope.dao.carAvailability.CarAvailabilityDAO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity(name = "location")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LocationDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "location")
    private List<CarAvailabilityDAO> availabilities;

}