package com.autoEuropeTest.autoEurope.controller.location;

import com.autoEuropeTest.autoEurope.dao.carAvailability.CarAvailabilityDAO;
import com.autoEuropeTest.autoEurope.dao.location.LocationDAO;
import com.autoEuropeTest.autoEurope.dto.CarAvailabilityDTO;
import com.autoEuropeTest.autoEurope.dto.LocationDTO;
import com.autoEuropeTest.autoEurope.mapper.CarAvailabilityMapper;
import com.autoEuropeTest.autoEurope.mapper.LocationMapper;
import com.autoEuropeTest.autoEurope.model.CarAvailability;
import com.autoEuropeTest.autoEurope.model.Location;
import com.autoEuropeTest.autoEurope.response.CarAvailabilityResponse;
import com.autoEuropeTest.autoEurope.response.LocationResponse;
import com.autoEuropeTest.autoEurope.service.location.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationControllerImplTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationControllerImpl locationController;



    @Mock
    private Location location ;

    @Mock
    private LocationDTO locationDTO;
    @Mock
    private LocationDAO locationDAO;

    @Mock
    private LocationMapper locationMapper;
    public LocationControllerImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetLocationById_ValidId_ReturnsLocation() {
        LocationResponse locationResponse = new LocationResponse(Collections.singletonList(location), "SUCCESS");

        when(locationService.getLocationById(location.getId())).thenReturn(ResponseEntity.ok(locationResponse));

        ResponseEntity<LocationResponse> response = locationController.getLocationById(location.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(locationResponse, response.getBody());
        verify(locationService, times(1)).getLocationById(location.getId());
    }

    @Test
    public void testGetLocationById_InvalidId_ReturnsNotFound() {

        when(locationService.getLocationById(location.getId())).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<LocationResponse> response = locationController.getLocationById(location.getId());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(locationService, times(1)).getLocationById(location.getId());
    }

    @Test
    public void testCreateLocation_ValidLocation_ReturnsCreatedLocation() {
        Long locationId = 1L;
        Location locationMock = new Location();
        locationMock.setId(locationId);
        LocationResponse locationResponse = new LocationResponse(
                Collections.<Location>singletonList(locationMapper.convertDAOToLocation((LocationDAO) Collections.singletonList(locationDAO))), "Location  created successfully."
        );
        when(locationService.createLocation(location)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(new LocationResponse(Collections.singletonList(location), "Location created successfully.")));

        ResponseEntity<LocationResponse> response = locationController.createLocation(location);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(location, response.getBody());
        verify(locationService, times(1)).createLocation(location);
    }

    @Test
    public void testUpdateLocation_ValidLocation_ReturnsUpdatedLocation() {
        Long locationId = 1L;
        Location locationMock = new Location();
        locationMock.setId(locationId);
        LocationResponse locationResponse = new LocationResponse(
                Collections.<Location>singletonList(locationMapper.convertDAOToLocation((LocationDAO) Collections.singletonList(locationDAO))), "Location  created successfully."
        );
        when(locationService.updateLocation(location.getId(), location)).thenReturn(ResponseEntity.ok(new LocationResponse(Collections.singletonList(locationMock), "Location updated successfully.")));

        ResponseEntity<LocationResponse> response = locationController.updateLocation(locationId, location);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals( locationResponse, response.getBody());
        verify(locationService, times(1)).updateLocation(locationId, location);
    }

    @Test
    public void testUpdateLocation_InvalidLocation_ReturnsNotFound() {
        Long locationId = 1L;
        Location locationMock = new Location();
        locationMock.setId(locationId);
        when(locationService.updateLocation(locationId , location)).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<LocationResponse> response = locationController.updateLocation(locationId, location);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(locationService, times(1)).updateLocation(locationId, location);
    }

    @Test
    public void testDeleteLocation_ExistingLocation_ReturnsNoContent() {
        Long locationId = 1L;

        LocationResponse locationResponse = new LocationResponse(null, "Location deleted successfully");
        when(locationService.deleteLocation(locationId)).thenReturn(ResponseEntity.ok(locationResponse));

        ResponseEntity<LocationResponse> response = locationController.deleteLocation(locationId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(locationResponse, response.getBody());
        verify(locationService, times(1)).deleteLocation(locationId);
    }

    @Test
    public void testDeleteLocation_NonExistingLocation_ReturnsNotFound() {
        Long locationId = 1L;

        LocationResponse locationResponse = new LocationResponse(null, "Location not found");
        when(locationService.deleteLocation(locationId)).thenReturn(ResponseEntity.ok(locationResponse));

        ResponseEntity<LocationResponse> response = locationController.deleteLocation(locationId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(locationResponse, response.getBody());
        verify(locationService, times(1)).deleteLocation(locationId);
    }

    @Test
    public void testGetAllLocations_ReturnsListOfLocations() {
        LocationDTO locationDTO = new LocationDTO();
        LocationResponse locationResponse = new LocationResponse(null, "Locations found");
        ResponseEntity<LocationResponse> expectedResponse = ResponseEntity.ok(locationResponse);
        when(locationService.getAllLocations()).thenReturn(expectedResponse);

        ResponseEntity<LocationResponse> response = locationController.getAllLocations();

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
        verify(locationService, times(1)).getAllLocations();
    }
}
