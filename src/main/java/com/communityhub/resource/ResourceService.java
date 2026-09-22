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

    public Optional<Resource> updateResource(Long id, Resource updatedResource) {
        return resourceRepository.findById(id).map(existingResource -> {
            existingResource.setResourceTitle(updatedResource.getResourceTitle());
            existingResource.setResourceDescription(updatedResource.getResourceDescription());
            existingResource.setResourceCategory(updatedResource.getResourceCategory());
            existingResource.setResourceTime(updatedResource.getResourceTime());
            existingResource.setResourceLocation(updatedResource.getResourceLocation());
            existingResource.setResourcePrice(updatedResource.getResourcePrice());
            existingResource.setContactName(updatedResource.getContactName());
            existingResource.setContactEmail(updatedResource.getContactEmail());
            existingResource.setContactPhone(updatedResource.getContactPhone());
            existingResource.setContactWebsiteUrl(updatedResource.getContactWebsiteUrl());
            return resourceRepository.save(existingResource);
        });
    }

    public Optional<Resource> patchResource(Long id, Resource patch) {
        return resourceRepository.findById(id).map(existingResource -> {
            if (patch.getResourceTitle() != null) {
                existingResource.setResourceTitle(patch.getResourceTitle());
            }
            if (patch.getResourceDescription() != null) {
                existingResource.setResourceDescription(patch.getResourceDescription());
            }
            if (patch.getResourceCategory() != null) {
                existingResource.setResourceCategory(patch.getResourceCategory());
            }
            if (patch.getResourceTime() != null) {
                existingResource.setResourceTime(patch.getResourceTime());
            }
            if (patch.getResourceLocation() != null) {
                existingResource.setResourceLocation(patch.getResourceLocation());
            }
            if (patch.getResourcePrice() != null) {
                existingResource.setResourcePrice(patch.getResourcePrice());
            }
            if (patch.getContactName() != null) {
                existingResource.setContactName(patch.getContactName());
            }
            if (patch.getContactEmail() != null) {
                existingResource.setContactEmail(patch.getContactEmail());
            }
            if (patch.getContactPhone() != null) {
                existingResource.setContactPhone(patch.getContactPhone());
            }
            if (patch.getContactWebsiteUrl() != null) {
                existingResource.setContactWebsiteUrl(patch.getContactWebsiteUrl());
            }
            return resourceRepository.save(existingResource);
        });
    }
}
