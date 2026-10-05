package com.communityhub.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = {"http://localhost:5173", "https://commiunity-hub-frontend.vercel.app"})
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        if (request.userName() == null || request.userEmail() == null || request.password() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Name, email, and password are required"));
        }

        if (userRepository.findByUserEmailIgnoreCase(request.userEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Email already in use"));
        }

        String hashed = BCrypt.withDefaults().hashToString(12, request.password().toCharArray());

        User user = new User();
        user.setUserName(request.userName());
        user.setUserEmail(request.userEmail());
        user.setUserPhone(request.userPhone());
        user.setUserAddress(request.userAddress());
        user.setProfilePicUrl(request.profilePicUrl());
        user.setPassword(hashed);

        User saved = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request.userEmail() == null || request.password() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email and password are required"));
        }

        Optional<User> found = userRepository.findByUserEmailIgnoreCase(request.userEmail());
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid email or password"));
        }

        User user = found.get();
        BCrypt.Result result = BCrypt.verifyer().verify(request.password().toCharArray(), user.getPassword());
        if (!result.verified) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid email or password"));
        }

        return ResponseEntity.ok(user);
    }

    public record SignupRequest(String userName, String userEmail, String password,
                                String userPhone, String userAddress, String profilePicUrl) {}

    public record LoginRequest(String userEmail, String password) {}
}
