# -*- coding: utf-8 -*-
import scrapy


class DoCrawlSpider(scrapy.Spider):
    name = 'do_crawl'
    allowed_domains = ['http://www.javaboy.org/']
    start_urls = ['http://http://www.javaboy.org//']

    def parse(self, response):
        print(response.text)
