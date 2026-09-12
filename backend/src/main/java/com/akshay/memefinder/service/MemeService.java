package com.akshay.memefinder.service;

import com.akshay.memefinder.dto.MemeResult;
import com.akshay.memefinder.exception.MemeSearchException;
import com.akshay.memefinder.source.ImgflipSource;
import com.akshay.memefinder.source.MemedroidSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Tells Spring that this class contains service/business logic.
// Spring will automatically create and manage an object of this class.
@Service
public class MemeService {

    private final MemedroidSource memedroidSource;
    private final ImgflipSource imgflipSource;
    public MemeService(MemedroidSource memedroidSource, ImgflipSource imgflipSource) {
        this.memedroidSource = memedroidSource;
        this.imgflipSource = imgflipSource;
    }

    public List<MemeResult> searchMeme(String query) {

        List<MemeResult> memes = new ArrayList<>();

        boolean memedroidFailed = false;
        boolean imgflipFailed = false;

        try {
            List<MemeResult> memedroidMemes = memedroidSource.search(query)
                    .stream()
                    .limit(10)
                    .toList();

            memes.addAll(memedroidMemes);

        } catch (Exception e) {
            memedroidFailed = true;
            System.out.println("Memedroid failed: " + e.getMessage());
        }

        try {
            List<MemeResult> imgflipMemes = imgflipSource.search(query)
                    .stream()
                    .limit(10)
                    .toList();

            memes.addAll(imgflipMemes);

        } catch (Exception e) {
            imgflipFailed = true;
            System.out.println("Imgflip failed: " + e.getMessage());
        }

        if (memedroidFailed && imgflipFailed) {
            throw new MemeSearchException(
                    "All meme sources are currently unavailable"
            );
        }

        return memes.stream().limit(20).toList();
    }
}