package org.wayne.crawl.crawlbyselenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrawlBySeleniumApplicationTests {

    @Test
    void contextLoads() {
        System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080");
//        driver.get("http://www.itest.info");

        String title = driver.getTitle();
        System.out.printf(title);

        driver.close();

    }

}
