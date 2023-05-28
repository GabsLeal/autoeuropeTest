package com.autoEuropeTest.autoEurope.dao.carAvailability;

import com.autoEuropeTest.autoEurope.dao.location.LocationDAO;
import com.autoEuropeTest.autoEurope.dao.car.CarDAO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "car_availabity")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CarAvailabilityDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarDAO carDAO;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationDAO locationDAO;

    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
}
