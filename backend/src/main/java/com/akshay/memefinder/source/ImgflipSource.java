package com.akshay.memefinder.source;

import com.akshay.memefinder.dto.MemeResult;
import com.akshay.memefinder.exception.MemeSearchException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImgflipSource {

    public List<MemeResult> search(String query) {

        try {

                String url =
                        "https://imgflip.com/memesearch?q=" + query;

                Document document = Jsoup
                        .connect(url)
                        .get();

            Elements memeImages = document.select("img.shadow");

            List<MemeResult> memes = new ArrayList<>();

            for (Element image : memeImages) {

                // Convert Imgflip's relative image URL into a complete URL
                String imageUrl = image.absUrl("src");

                // Get the meme title from the alt attribute
                String title = image.attr("alt");

                MemeResult memeResult = new MemeResult(
                        title,
                        imageUrl,
                        "Imgflip"
                );

                memes.add(memeResult);
            }

            return memes;

        } catch (IOException e) {

            throw new MemeSearchException(
                    "Failed to fetch memes from Imgflip"
            );
        }
    }
}