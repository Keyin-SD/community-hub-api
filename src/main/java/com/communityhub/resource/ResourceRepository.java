package com.communityhub.resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Iterable<Resource> findByResourceCategoryIgnoreCase(String resourceCategory);

    Iterable<Resource> findByResourceTitleContainingIgnoreCase(String resourceTitle);

    Iterable<Resource> findByContactNameContainingIgnoreCase(String contactName);

    Iterable<Resource> findByResourceLocationContainingIgnoreCase(String resourceLocation);
}
