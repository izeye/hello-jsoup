package learningtest.org.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Tests for {@link Jsoup}.
 *
 * @author Johnny Lim
 */
class JsoupTests {

    @Test
    void connect() throws IOException {
        Document document = Jsoup.connect("https://en.wikipedia.org/wiki/Main_Page").get();
        System.out.println(document.title());

        Elements newsHeadlines = document.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            String title = headline.attr("title");
            String url = headline.absUrl("href");
            System.out.println(title + ": " + url);
        }
    }

    @Test
    void parse() throws IOException {
        Document document = Jsoup.parse(new File("src/test/resources/thymeleaf.html"));
        Elements elements = document.body().select("*");
        for (Element element : elements) {
            Attributes attributes = element.attributes();
            for (Attribute attribute : attributes) {
                String key = attribute.getKey();
                String value = attribute.getValue();
                System.out.println("Attribute: " + key + ": " + value);
            }
            System.out.println("Element: " + element);
        }
    }

    @Test
    void printAllThymeleafAttributeValues() throws IOException {
        String path = "src/test/resources/thymeleaf.html";

        Set<String> values = getAllThymeleafAttributeValues(path);
        values.forEach(System.out::println);
    }

    private static Set<String> getAllThymeleafAttributeValues(String path) throws IOException {
        Set<String> values = new TreeSet<>();
        Document document = Jsoup.parse(new File(path));
        Elements elements = document.body().select("*");
        for (Element element : elements) {
            Attributes attributes = element.attributes();
            for (Attribute attribute : attributes) {
                String value = attribute.getValue();
                if (attribute.getKey().startsWith("th:")) {
                    values.add(value);
                }
            }
        }
        return values;
    }

}
