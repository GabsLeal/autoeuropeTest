package com.autoEuropeTest.autoEurope.dao.carAvailability;

import com.autoEuropeTest.autoEurope.dao.location.LocationDAO;
import com.autoEuropeTest.autoEurope.dao.car.CarDAO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "car_availability")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarAvailabilityDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private CarDAO car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private LocationDAO location;

    @NonNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @NonNull
    @Column(name = "end_date")
    private LocalDate endDate;

    @NonNull
    @Column(name = "price")
    private BigDecimal price;
}
