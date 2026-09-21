package com.communityhub.resource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private double resourcePrice;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private String contactWebsiteUrl;

    public Resource(String resourceTitle, String resourceDescription, String resourceCategory, String resourceTime, String resourceLocation, double resourcePrice, String contactName, String contactEmail, String contactPhone, String contactWebsiteUrl) {
        this.resourceTitle = resourceTitle;
        this.resourceDescription = resourceDescription;
        this.resourceCategory = resourceCategory;
        this.resourceTime = resourceTime;
        this.resourceLocation = resourceLocation;
        this.resourcePrice = resourcePrice;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
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

    public double getResourcePrice() {
        return resourcePrice;
    }

    public void setResourcePrice(double resourcePrice) {
        this.resourcePrice = resourcePrice;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactWebsiteUrl() {
        return contactWebsiteUrl;
    }

    public void setContactWebsiteUrl(String contactWebsiteUrl) {
        this.contactWebsiteUrl = contactWebsiteUrl;
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
        sb.append(", contactName='").append(contactName).append('\'');
        sb.append(", contactEmail='").append(contactEmail).append('\'');
        sb.append(", contactPhone='").append(contactPhone).append('\'');
        sb.append(", contactWebsiteUrl='").append(contactWebsiteUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
