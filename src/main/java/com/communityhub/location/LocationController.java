package com.communityhub.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "https://your-frontend-url.com"})
@RequestMapping("api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("")
    public ResponseEntity<String> createNewLocation(@RequestBody Location location) {
        locationService.createLocation(location);
        return ResponseEntity.status(201).body("Location created successfully");
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Location>> getAllLocations() {
        Iterable<Location> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Location location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }
}
