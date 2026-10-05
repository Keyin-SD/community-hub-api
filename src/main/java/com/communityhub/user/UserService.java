package com.communityhub.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        if (user == null || user.getUserName() == null) {
            throw new IllegalArgumentException("User cannot be null or have null name");
        }
        user.setUserId(null);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existing -> {
            existing.setUserName(updatedUser.getUserName());
            existing.setUserEmail(updatedUser.getUserEmail());
            existing.setUserPhone(updatedUser.getUserPhone());
            existing.setUserAddress(updatedUser.getUserAddress());
            existing.setProfilePicUrl(updatedUser.getProfilePicUrl());
            return userRepository.save(existing);
        });
    }

    public Optional<User> patchUser(Long id, User patch) {
        return userRepository.findById(id).map(existing -> {
            if (patch.getUserName() != null) {
                existing.setUserName(patch.getUserName());
            }
            if (patch.getUserEmail() != null) {
                existing.setUserEmail(patch.getUserEmail());
            }
            if (patch.getUserPhone() != null) {
                existing.setUserPhone(patch.getUserPhone());
            }
            if (patch.getUserAddress() != null) {
                existing.setUserAddress(patch.getUserAddress());
            }
            if (patch.getProfilePicUrl() != null) {
                existing.setProfilePicUrl(patch.getProfilePicUrl());
            }
            return userRepository.save(existing);
        });
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
