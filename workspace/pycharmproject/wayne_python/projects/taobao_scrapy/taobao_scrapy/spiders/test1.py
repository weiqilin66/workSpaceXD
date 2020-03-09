# -*- coding: utf-8 -*-
import scrapy
import re


class Test1Spider(scrapy.Spider):
    name = 'test1'
    allowed_domains = ['https://s.taobao.com']
    start_urls = ['https://s.taobao.com/search?ie=utf8&initiative_id=staobaoz_20200309&stats_click=search_radio_all%3A1&js=1&imgfile=&q=%E5%AE%9D%E5%8F%AF%E6%A2%A6%E5%89%91%E7%9B%BE&suggest=history_1&_input_charset=utf-8&wq=%E5%AE%9D%E5%8F%AF%E6%A2%A6&suggest_query=%E5%AE%9D%E5%8F%AF%E6%A2%A6&source=suggest']

    def parse(self, response):
        print(response.text)
        goods_match = re.search(r'g_page_config = (.*?)}};', response.text)
        print('*'*50)
        print(goods_match)