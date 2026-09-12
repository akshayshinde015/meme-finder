package com.akshay.memefinder.controller;

import com.akshay.memefinder.dto.MemeResult;
import com.akshay.memefinder.service.MemeService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

// Marks this class as a REST controller
@RestController

// Allows requests coming from our React development server
@CrossOrigin(origins = "http://localhost:5173")

// Base URL for all meme endpoints
@RequestMapping("/api/memes")
public class MemeController {

    private final MemeService memeService;

    // Spring injects MemeService through constructor injection
    public MemeController(MemeService memeService) {
        this.memeService = memeService;
    }

    // Handles requests like:
    // GET /api/memes/search?query=java
    @GetMapping("/search")
    public List<MemeResult> searchMeme(@RequestParam String query) throws IOException {

        // Pass the query to the service and return the meme results
        return memeService.searchMeme(query);
    }
}