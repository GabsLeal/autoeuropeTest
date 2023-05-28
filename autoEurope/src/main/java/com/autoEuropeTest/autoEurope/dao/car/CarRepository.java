package com.autoEuropeTest.autoEurope.dao.car;

import com.autoEuropeTest.autoEurope.dao.car.CarDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository <CarDAO, Long>{
}
