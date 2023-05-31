package com.autoEuropeTest.autoEurope.dao.car;

import com.autoEuropeTest.autoEurope.dao.location.LocationDAO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity(name = "car")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "brand")
    private String brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private LocationDAO location;
}
