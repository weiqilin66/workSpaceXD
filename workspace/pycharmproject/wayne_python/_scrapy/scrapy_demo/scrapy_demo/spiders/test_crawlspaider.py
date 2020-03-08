# -*- coding: utf-8 -*-
import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule


class TestCrawlspaiderSpider(CrawlSpider):
    name = 'test_crawlspaider'
    allowed_domains = ['www.baidu.com']
    start_urls = ['http://www.baidu.com/']

    rules = (
        # callback 提取的每一个链接的处理方法 flow是否跟进 链接进去的网页是否还需要根据这个规则提取网页并执行这个rule 调度器自动去重
        Rule(LinkExtractor(allow=r'Items/'), callback='parse_item', follow=True),
    )

    def parse_item(self, response):
        item = {}
        #item['domain_id'] = response.xpath('//input[@id="sid"]/@value').get()
        #item['name'] = response.xpath('//div[@id="name"]').get()
        #item['description'] = response.xpath('//div[@id="description"]').get()
        return item
