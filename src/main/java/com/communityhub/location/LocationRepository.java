package com.communityhub.location;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByLocationNameIgnoreCase(String locationName);
    boolean existsByLocationNameIgnoreCaseAndLocationAddressIgnoreCase(String locationName, String locationAddress);
    Optional<Location> findByLocationNameIgnoreCase(String locationName);
    Optional<Location> findByLocationNameIgnoreCaseAndLocationAddressIgnoreCase(String locationName, String locationAddress);
}
