package com.communityhub.randomthing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class RandomController {

    @GetMapping("/Hello")
    public ResponseEntity<java.lang.String> get() {
        return ResponseEntity.ok("Hello, World!");
    }
}
