package web;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawlerSupport {
    private List<String> links = new LinkedList<String>();
    private Document document;
    private static final String AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

    void crawl(String url) {
        try {
            Connection connection = Jsoup.connect(url).userAgent(AGENT);
            document = connection.get();
            if(connection.response().statusCode() == 200) {
                System.out.println("COOL");
            }
            if(!connection.response().contentType().contains("text/html")) {
                System.out.println("ERROR");
                System.out.println("This page doesn't contain HTML");
                return;
            }
            Elements linksOnPage = document.select("a[href]");
            System.out.println("Found: " + linksOnPage.size());
            for (Element href: linksOnPage) {
                links.add(href.absUrl("href"));
            }
        } catch (IOException e) {
            System.out.println("Smth wrong with connection");
            e.printStackTrace();
        }
    }

    List<Integer> searchWord(String[] words) {
        if(document == null) {
            System.out.println("Data was not received from the page");
            return null;
        }
        List<Integer> countDetectedWords = new ArrayList<Integer>(6);
        int commonCount = 0;
        System.out.println("Search .....");
        String text = document.body().text();
        for (String wordBuff: words) {
            int bufParam = getWordCountOnPage(wordBuff, text);
            commonCount += bufParam;
            countDetectedWords.add(bufParam);
        }
        countDetectedWords.add(commonCount);
        return countDetectedWords;
    }

    private int getWordCountOnPage (String word, String textFromPage) {
        int count = 0;
        Pattern pattern = Pattern.compile(word + "|" + word.toLowerCase());
        Matcher matcher = pattern.matcher(textFromPage);

        while(matcher.find()) {
            count++;
        }
        return count;
    }

    List<String> getLinks() {
        return links;
    }
}
