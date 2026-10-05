package com.communityhub.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query("SELECT r FROM Resource r LEFT JOIN r.location l LEFT JOIN l.city c LEFT JOIN r.postedBy u " +
           "WHERE LOWER(r.resourceTitle) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(r.resourceCategory) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(r.resourceDescription) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(l.locationName) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(c.cityName) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(u.userName) LIKE LOWER(CONCAT('%', :q, '%'))")
    List<Resource> searchAll(@Param("q") String query);
    Page<Resource> findAll(Pageable pageable);
    Iterable<Resource> findByResourceCategoryIgnoreCase(String resourceCategory);

    Iterable<Resource> findByResourceTitleContainingIgnoreCase(String resourceTitle);

    Iterable<Resource> findByLocation_LocationNameContainingIgnoreCase(String locationName);

    Iterable<Resource> findByLocation_City_CityNameContainingIgnoreCase(String cityName);

    List<Resource> findByPostedBy_UserId(Long userId);
}
