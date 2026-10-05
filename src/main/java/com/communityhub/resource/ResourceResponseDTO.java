package com.communityhub.resource;

import com.communityhub.location.Location;

public class ResourceResponseDTO {
    private Long resourceId;
    private String resourceTitle;
    private String resourceDescription;
    private String resourceCategory;
    private String resourceTime;
    private String resourceLocation;
    private Double resourcePrice;
    private String contactWebsiteUrl;
    private Location location;
    private UserSummary postedBy;

    public static class UserSummary {
        private Long userId;
        private String userName;
        private String profilePicUrl;

        public UserSummary(Long userId, String userName, String profilePicUrl) {
            this.userId = userId;
            this.userName = userName;
            this.profilePicUrl = profilePicUrl;
        }

        public Long getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public String getProfilePicUrl() {
            return profilePicUrl;
        }
    }

    public static ResourceResponseDTO from(Resource resource) {
        ResourceResponseDTO dto = new ResourceResponseDTO();
        dto.resourceId = resource.getResourceId();
        dto.resourceTitle = resource.getResourceTitle();
        dto.resourceDescription = resource.getResourceDescription();
        dto.resourceCategory = resource.getResourceCategory();
        dto.resourceTime = resource.getResourceTime();
        dto.resourceLocation = resource.getResourceLocation();
        dto.resourcePrice = resource.getResourcePrice();
        dto.contactWebsiteUrl = resource.getContactWebsiteUrl();
        dto.location = resource.getLocation();
        if (resource.getPostedBy() != null) {
            dto.postedBy = new UserSummary(
                    resource.getPostedBy().getUserId(),
                    resource.getPostedBy().getUserName(),
                    resource.getPostedBy().getProfilePicUrl());
        }
        return dto;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public String getResourceTitle() {
        return resourceTitle;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public String getResourceCategory() {
        return resourceCategory;
    }

    public String getResourceTime() {
        return resourceTime;
    }

    public String getResourceLocation() {
        return resourceLocation;
    }

    public Double getResourcePrice() {
        return resourcePrice;
    }

    public String getContactWebsiteUrl() {
        return contactWebsiteUrl;
    }

    public Location getLocation() {
        return location;
    }

    public UserSummary getPostedBy() {
        return postedBy;
    }
}
