package com.autoEuropeTest.autoEurope.dao.location;

import com.autoEuropeTest.autoEurope.dao.location.LocationDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository  extends JpaRepository<LocationDAO, Long> {
}
