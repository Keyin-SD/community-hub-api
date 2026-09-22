package com.communityhub.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;


    public void createResource(Resource resource) {
        if(resource == null || resource.getResourceTitle() == null) {
            throw new IllegalArgumentException("Resource cannot be null or have null fields");
        }
        resourceRepository.save(resource);
    }

    public Iterable<Resource> getAllResources() {
        if(resourceRepository.findAll().isEmpty()) {
            throw new IllegalArgumentException("No resources found");
        }
        return resourceRepository.findAll();
    }

    public Optional<Resource> searchResourceById(Long resourceId) {
        if(resourceId == null) {
            throw new IllegalArgumentException("Resource ID cannot be null");
        }
        return resourceRepository.findById(resourceId);
    }

    public Iterable<Resource> searchResourcesByCategory(String category) {
        return resourceRepository.findByResourceCategoryIgnoreCase(category);
    }

    public Iterable<Resource> searchResourcesByTitle(String title) {
        return resourceRepository.findByResourceTitleContainingIgnoreCase(title);
    }

    public Iterable<Resource> searchResourcesByContactName(String contactName) {
        return resourceRepository.findByContactNameContainingIgnoreCase(contactName);
    }

    public Iterable<Resource> searchResourcesByLocation(String location) {
        return resourceRepository.findByResourceLocationContainingIgnoreCase(location);
    }

    public void removeResource(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Resource ID cannot be null");
        }
        resourceRepository.deleteById(id);
    }
}
