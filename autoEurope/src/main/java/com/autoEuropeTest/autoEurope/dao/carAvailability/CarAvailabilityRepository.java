package com.autoEuropeTest.autoEurope.dao.carAvailability;

import com.autoEuropeTest.autoEurope.dao.carAvailability.CarAvailabilityDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarAvailabilityRepository extends JpaRepository<CarAvailabilityDAO,Long> {

}
