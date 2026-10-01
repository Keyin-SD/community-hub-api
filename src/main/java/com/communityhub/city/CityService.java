package com.communityhub.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public City createCity(City city) {
        if (city == null || city.getCityName() == null || city.getCityName().isBlank()) {
            throw new IllegalArgumentException("City name cannot be null or empty");
        }
        if (cityRepository.findByCityNameNormalized(city.getCityName()).isPresent()) {
            throw new IllegalArgumentException("City '" + city.getCityName() + "' already exists");
        }
        city.setCityId(null);
        return cityRepository.save(city);
    }

    public Iterable<City> getAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    public Optional<City> updateCity(Long id, City updated) {
        return cityRepository.findById(id).map(existing -> {
            if (updated.getCityName() != null) {
                existing.setCityName(updated.getCityName());
            }
            if (updated.getPopulation() != null) {
                existing.setPopulation(updated.getPopulation());
            }
            if (updated.getProvince() != null) {
                existing.setProvince(updated.getProvince());
            }
            return cityRepository.save(existing);
        });
    }

    public City findOrCreateCity(String cityName) {
        return cityRepository.findByCityNameNormalized(cityName)
                .orElseGet(() -> {
                    City newCity = new City(cityName);
                    return cityRepository.save(newCity);
                });
    }
}
