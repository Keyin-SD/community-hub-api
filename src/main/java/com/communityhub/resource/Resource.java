package com.communityhub.resource;

import com.communityhub.location.Location;
import com.communityhub.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;
    private String resourceTitle;
    private String resourceDescription;
    private String resourceCategory;
    private String resourceTime;
    private String resourceLocation;
    private Double resourcePrice;
    private String contactWebsiteUrl;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User postedBy;

    public Resource(String resourceTitle, String resourceDescription, String resourceCategory, String resourceTime, String resourceLocation, Double resourcePrice, String contactWebsiteUrl) {
        this.resourceTitle = resourceTitle;
        this.resourceDescription = resourceDescription;
        this.resourceCategory = resourceCategory;
        this.resourceTime = resourceTime;
        this.resourceLocation = resourceLocation;
        this.resourcePrice = resourcePrice;
        this.contactWebsiteUrl = contactWebsiteUrl;
    }

    public Resource() {
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceTitle() {
        return resourceTitle;
    }

    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public String getResourceCategory() {
        return resourceCategory;
    }

    public void setResourceCategory(String resourceCategory) {
        this.resourceCategory = resourceCategory;
    }

    public String getResourceTime() {
        return resourceTime;
    }

    public void setResourceTime(String resourceTime) {
        this.resourceTime = resourceTime;
    }

    public String getResourceLocation() {
        return resourceLocation;
    }

    public void setResourceLocation(String resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public Double getResourcePrice() {
        return resourcePrice;
    }

    public void setResourcePrice(Double resourcePrice) {
        this.resourcePrice = resourcePrice;
    }

    public String getContactWebsiteUrl() {
        return contactWebsiteUrl;
    }

    public void setContactWebsiteUrl(String contactWebsiteUrl) {
        this.contactWebsiteUrl = contactWebsiteUrl;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Resource{");
        sb.append("resourceId=").append(resourceId);
        sb.append(", resourceTitle='").append(resourceTitle).append('\'');
        sb.append(", resourceDescription='").append(resourceDescription).append('\'');
        sb.append(", resourceCategory='").append(resourceCategory).append('\'');
        sb.append(", resourceTime='").append(resourceTime).append('\'');
        sb.append(", resourceLocation='").append(resourceLocation).append('\'');
        sb.append(", resourcePrice=").append(resourcePrice);
        sb.append(", contactWebsiteUrl='").append(contactWebsiteUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
