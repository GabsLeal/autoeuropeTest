package com.autoEuropeTest.autoEurope.dao.location;

import com.autoEuropeTest.autoEurope.dao.carAvailability.CarAvailabilityDAO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "location")
@Data
@Builder
@AllArgsConstructor
public class LocationDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarAvailabilityDAO> availabilities = new ArrayList<>();
}
