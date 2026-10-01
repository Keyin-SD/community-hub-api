package com.communityhub.city;

import com.communityhub.location.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @Column(unique = true, nullable = false)
    private String cityName;
    private Long population;
    private String province;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Location> locations;

    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public City(String cityName, Long population, String province) {
        this.cityName = cityName;
        this.population = population;
        this.province = province;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return cityId != null && cityId.equals(city.cityId);
    }

    @Override
    public int hashCode() {
        return cityId != null ? cityId.hashCode() : 0;
    }
}
