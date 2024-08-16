package learningtest.org.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Tests for {@link Jsoup}.
 *
 * @author Johnny Lim
 */
class JsoupTests {

    @Test
    void test() throws IOException {
        Document document = Jsoup.connect("https://en.wikipedia.org/wiki/Main_Page").get();
        System.out.println(document.title());

        Elements newsHeadlines = document.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            String title = headline.attr("title");
            String url = headline.absUrl("href");
            System.out.println(title + ": " + url);
        }
    }

}
