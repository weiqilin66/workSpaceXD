package org.wayne.crawl.crawlbyselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlBySeleniumApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlBySeleniumApplication.class, args);
        System.setProperty("webdriver.chrome.driver", "D:\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080");
    }

}
