import org.junit.Before;
import org.junit.Test;
import web.WebCrawler;
import static org.junit.Assert.*;

public class TestWebCrawler {
    private WebCrawler webCrawler;
    private String[] wordsForSearch = {"Plugins", "Dicenchantment", "Patents", "Membership", "Watch everywhere"};
    private String url = "https://www.netflix.com/by/";

    @Before
    public void setup() {
        webCrawler = new WebCrawler();
    }

    @Test
    public void TestSearchMethod() {
        webCrawler.search(url, wordsForSearch);
        assertNotNull(webCrawler.getPageToVisit());
        assertNotNull(webCrawler.getPageVisited());
    }
}
