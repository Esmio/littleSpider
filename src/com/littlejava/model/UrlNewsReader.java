package com.littlejava.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class UrlNewsReader {

    public static NewsWithRelated read(String url) throws IOException {
        // Jsoup 读取和解析 页面
        Document doc = Jsoup.connect(url).get();
        Elements titleElements = doc.select("title");
        String title = titleElements.first().text();

        Elements contentElements = doc.select(".summary___3oqrM");
        String content = contentElements.first().text();

        /*
        Elements contentElements = doc.select("meta[name=description]");
        String content = contentElements.first().attr("content");
        */

        NewsWithRelated news = new NewsWithRelated(title, content);

        Elements relatedElements = doc.select(".row___3h219");
        Elements relatedElementsOld = doc.select(".timeline__container___1wEQp");
        if(relatedElements.size() > 0) {
            for (Element element : relatedElements) {
                Elements aLink = element.select("a.url___3iQHY");
                String relatedTitle = aLink.first().text();
                String relatedUrl = aLink.first().absUrl("href");

                news.addRelated(relatedTitle, relatedUrl);
            }
        } else if (relatedElementsOld.size() > 0) {
            for (Element element : relatedElementsOld) {
                Elements aLink = element.select("a.timeline-content___1IyVC");
                String relatedTitle = aLink.first().text();
                String relatedUrl = aLink.first().absUrl("href");

                news.addRelated(relatedTitle, relatedUrl);
            }
        }

        return news;
    }
}
