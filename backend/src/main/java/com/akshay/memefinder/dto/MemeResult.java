package com.akshay.memefinder.dto;

// DTO used to send meme information from our backend to the frontend.
public class MemeResult {

    // Title/description of the meme.
    private String title;

    // Direct URL of the meme image.
    private String imageUrl;

    // Website from which the meme was collected.
    private String source;

    // Creates a MemeResult object with all required meme information.
    public MemeResult(String title, String imageUrl, String source) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.source = source;
    }

    // Returns the meme title.
    public String getTitle() {
        return title;
    }

    // Returns the direct URL of the meme image.
    public String getImageUrl() {
        return imageUrl;
    }

    // Returns the website/source from which the meme was collected.
    public String getSource() {
        return source;
    }
}