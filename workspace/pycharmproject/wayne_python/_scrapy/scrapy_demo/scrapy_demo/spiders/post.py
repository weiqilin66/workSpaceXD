# -*- coding: utf-8 -*-
import scrapy
from scrapy_demo.items import ScrapyDemoItem
""" 发送post 请求 传参"""
class PostSpider(scrapy.Spider):
    name = 'post'
    allowed_domains = ['fanyi.baidu.com']
    # start_urls = ['http://fanyi.baidu.com/']

    def start_requests(self):
        post_url = 'http://fanyi.baidu.com/sug'
        form_data={
            'kw': 'wolf'
        }
        yield scrapy.FormRequest(url=post_url,formdata=form_data)

    def parse(self, response):
        item = ScrapyDemoItem()
        print('*' *50)
        print(response.text)
        # 传参
        yield scrapy.Request(url='',callback=self.parse_detail,meta={'item':item})

    def parse_detail(self,response):
        item =response.meta['item']