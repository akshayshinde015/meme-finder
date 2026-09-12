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
public class MemedroidSource {

    public List<MemeResult> search(String query) {

        try {
            String url =
                    "https://www.memedroid.com/search?query=" + query;

            Document document = Jsoup
                    .connect(url)
                    .get();

            Elements memeImages =
                    document.select("img.img-responsive.grey-background");

            List<MemeResult> memes = new ArrayList<>();

            for (Element image : memeImages) {

                String imageUrl = image.attr("src");
                String title = image.attr("alt");

                MemeResult meme =
                        new MemeResult(
                                title,
                                imageUrl,
                                "Memedroid"
                        );

                memes.add(meme);
            }

            return memes;

        } catch (IOException e) {

            throw new MemeSearchException(
                    "Failed to fetch memes from Memedroid"
            );
        }
    }
}