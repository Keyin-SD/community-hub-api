package com.communityhub.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCityNameIgnoreCase(String cityName);
    boolean existsByCityNameIgnoreCase(String cityName);

    @Query("SELECT c FROM City c WHERE LOWER(REPLACE(REPLACE(REPLACE(c.cityName, '.', ''), '''', ''), '-', '')) = LOWER(REPLACE(REPLACE(REPLACE(:name, '.', ''), '''', ''), '-', ''))")
    Optional<City> findByCityNameNormalized(@Param("name") String name);
}
