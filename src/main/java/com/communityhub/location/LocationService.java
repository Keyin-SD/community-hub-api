package com.communityhub.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    private boolean locationExists(Location location) {
        if (location.getLocationAddress() != null) {
            return locationRepository.existsByLocationNameIgnoreCaseAndLocationAddressIgnoreCase(
                    location.getLocationName(), location.getLocationAddress());
        }
        return locationRepository.existsByLocationNameIgnoreCase(location.getLocationName());
    }

    public void createLocation(Location location) {
        if(location == null || location.getLocationName() == null) {
            throw new IllegalArgumentException("Location cannot be null or have null fields");
        }

        if(locationExists(location)) {
            throw new IllegalArgumentException("Location with name " + location.getLocationName() + " at that address already exists");
        }
        location.setLocationId(null);
        locationRepository.save(location);
    }

    public Iterable<Location> getAllLocations() {
        if(locationRepository.findAll().isEmpty()) {
            throw new IllegalArgumentException("No locations found");
        }
        return locationRepository.findAll();
    }


    public Location getLocationById(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(() -> new IllegalArgumentException("Location not found"));
    }
}
