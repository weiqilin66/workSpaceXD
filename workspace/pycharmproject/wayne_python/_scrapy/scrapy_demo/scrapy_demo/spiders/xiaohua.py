# -*- coding: utf-8 -*-
import scrapy,os
from scrapy_demo.items import ScrapyDemoItem


class XiaohuaSpider(scrapy.Spider):
    name = 'xiaohua'
    allowed_domains = ['www.521609.com']
    start_urls = ['http://www.521609.com/daxuexiaohua//']

    # 分页
    url = 'http://www.521609.com/daxuexiaohua/list3{}.html'
    page = 1

    def parse(self, response):
        li_list = response.xpath("//div[@class='index_img list_center']//ul//li")
        # print(li_list.extract())
        for li in li_list:
            # item对象
            item = ScrapyDemoItem()
            name = li.xpath('./a/img/@alt').extract_first()
            img_url = 'http://www.521609.com'+li.xpath('./a/img/@src').extract_first()
            item['name'] = name
            item['img_url'] = img_url
            yield item
            # yield scrapy.Request(url=item['img_url'] ,callback= self.download) 防盗链

        if self.page <1:
            self.page+=1
            print('开始爬取第%s页' % self.page)
            url = self.url.format(self.page)
            yield scrapy.Request(url,callback=self.parse)

    def download(self,response):
        dir = 'd:/img'
        img_url = response.url
        name = os.path.basename(img_url)
        print('os.path.basename用法'+name)
        img_path = os.path.join(dir,name)
        with open(img_path,'wb') as fp:
            fp.write(response.body)

