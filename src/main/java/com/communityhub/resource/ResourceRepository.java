package com.communityhub.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Page<Resource> findAll(Pageable pageable);
    Iterable<Resource> findByResourceCategoryIgnoreCase(String resourceCategory);

    Iterable<Resource> findByResourceTitleContainingIgnoreCase(String resourceTitle);

    Iterable<Resource> findByContactNameContainingIgnoreCase(String contactName);

    Iterable<Resource> findByLocation_LocationNameContainingIgnoreCase(String locationName);

    Iterable<Resource> findByLocation_City_CityNameContainingIgnoreCase(String cityName);
}
