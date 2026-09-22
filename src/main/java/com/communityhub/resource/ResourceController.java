package com.communityhub.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/resources")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping()
    public ResponseEntity<String> createNewResource(@RequestBody Resource resource) {
        resourceService.createResource(resource);
        return ResponseEntity.status(HttpStatus.CREATED).body("Resource created successfully");
    }

    @GetMapping("allResources")
    public ResponseEntity<Iterable<Resource>> getAllResources() {
        Iterable<Resource> resources = resourceService.getAllResources();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("searchResourceById/{resourceId}")
    public ResponseEntity<Resource> getResourcesById(@PathVariable Long resourceId) {
        return resourceService.searchResourceById(resourceId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("searchByCategory/{category}")
    public ResponseEntity<Iterable<Resource>> searchByCategory(@PathVariable String category) {
        return ResponseEntity.ok(resourceService.searchResourcesByCategory(category));
    }

    @GetMapping("searchByTitle/{title}")
    public ResponseEntity<Iterable<Resource>> searchByTitle(@PathVariable String title) {
        return ResponseEntity.ok(resourceService.searchResourcesByTitle(title));
    }

    @GetMapping("searchByContactName/{contactName}")
    public ResponseEntity<Iterable<Resource>> searchByContactName(@PathVariable String contactName) {
        return ResponseEntity.ok(resourceService.searchResourcesByContactName(contactName));
    }

    @GetMapping("searchByLocation/{location}")
    public ResponseEntity<Iterable<Resource>> searchByLocation(@PathVariable String location) {
        return ResponseEntity.ok(resourceService.searchResourcesByLocation(location));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteResource(@PathVariable Long id) {
        if (resourceService.searchResourceById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
        }

        resourceService.removeResource(id);
        return ResponseEntity.ok("Resource deleted successfully");
    }
}
