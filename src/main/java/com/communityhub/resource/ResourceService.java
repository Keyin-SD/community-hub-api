package com.communityhub.resource;

import com.communityhub.city.City;
import com.communityhub.city.CityService;
import com.communityhub.location.Location;
import com.communityhub.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CityService cityService;

    private void resolveLocation(Resource resource, Location incomingOverride) {
        Location incoming = incomingOverride != null ? incomingOverride : resource.getLocation();
        if (incoming != null && incoming.getLocationName() != null) {
            String name = incoming.getLocationName();
            String address = incoming.getLocationAddress();

            // Resolve city if provided
            City city = null;
            if (incoming.getCity() != null && incoming.getCity().getCityName() != null) {
                City incomingCity = incoming.getCity();
                city = cityService.findOrCreateCity(
                        incomingCity.getCityName(),
                        incomingCity.getPopulation(),
                        incomingCity.getProvince());
            }

            Location location;
            if (address != null) {
                City finalCity = city;
                location = locationRepository
                        .findByLocationNameIgnoreCaseAndLocationAddressIgnoreCase(name, address)
                        .orElseGet(() -> {
                            Location newLocation = new Location();
                            newLocation.setLocationName(name);
                            newLocation.setLocationAddress(address);
                            newLocation.setCity(finalCity);
                            return locationRepository.save(newLocation);
                        });
            } else {
                City finalCity = city;
                location = locationRepository
                        .findByLocationNameIgnoreCase(name)
                        .orElseGet(() -> {
                            Location newLocation = new Location();
                            newLocation.setLocationName(name);
                            newLocation.setCity(finalCity);
                            return locationRepository.save(newLocation);
                        });
            }
            if (city != null && !city.equals(location.getCity())) {
                location.setCity(city);
                locationRepository.save(location);
            }
            resource.setLocation(location);
        } else if (resource.getResourceLocation() != null) {
            Location location = locationRepository
                    .findByLocationNameIgnoreCase(resource.getResourceLocation())
                    .orElseGet(() -> {
                        Location newLocation = new Location();
                        newLocation.setLocationName(resource.getResourceLocation());
                        return locationRepository.save(newLocation);
                    });
            resource.setLocation(location);
        }
    }

    public Resource createResource(Resource resource) {
        if (resource == null || resource.getResourceTitle() == null) {
            throw new IllegalArgumentException("Resource cannot be null or have null fields");
        }
        resource.setResourceId(null);
        resolveLocation(resource, null);
        return resourceRepository.save(resource);
    }

    public Page<Resource> getAllResources(Pageable pageable) {
        return resourceRepository.findAll(pageable);
    }

    public Optional<Resource> searchResourceById(Long resourceId) {
        if (resourceId == null) {
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

    public List<Resource> getResourcesByUserId(Long userId) {
        return resourceRepository.findByPostedBy_UserId(userId);
    }

    public Iterable<Resource> searchResourcesByLocation(String location) {
        return resourceRepository.findByLocation_LocationNameContainingIgnoreCase(location);
    }

    public Iterable<Resource> searchResourcesByCity(String cityName) {
        return resourceRepository.findByLocation_City_CityNameContainingIgnoreCase(cityName);
    }

    public List<Resource> searchAll(String query) {
        return resourceRepository.searchAll(query);
    }

    public void removeResource(Long id) {
        if (id == null) {
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
            existingResource.setContactWebsiteUrl(updatedResource.getContactWebsiteUrl());
            resolveLocation(existingResource, updatedResource.getLocation());
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
            if (patch.getContactWebsiteUrl() != null) {
                existingResource.setContactWebsiteUrl(patch.getContactWebsiteUrl());
            }
            if (patch.getLocation() != null) {
                resolveLocation(existingResource, patch.getLocation());
            } else if (patch.getResourceLocation() != null) {
                resolveLocation(existingResource, null);
            }
            return resourceRepository.save(existingResource);
        });
    }
}
