# -*- coding: utf-8 -*-
import scrapy
from test_scrapy.items import TestScrapyItem

"""无法导入 将根目录(外层test_scrapy) make directory as  Sources Root """


class QiushibaikeSpider(scrapy.Spider):
    name = 'qiushibaike'
    # 允许的域名列表 限制只能爬取该域名下的url数据
    allowed_domains = ['www.qiushibaike.com']
    # 起始url列表
    start_urls = ['http://www.qiushibaike.com/']
    # 爬取多页  next_page= url.format(page_no)
    url ='xx/{}'
    page= 2

    # 解析函数(重写), 响应来了就执行该方法 ,response就是响应内容, 返回可迭代对象
    def parse(self, response):
        # 创建对象
        item = TestScrapyItem()
        print('----------------------------------- start-------------------------------------------')
        print(response)
        # 使用xpath()
        img_div_list = response.xpath("//a[@class='list']")
        img_div_list = img_div_list.xpath("//img/@src")
        print(type(img_div_list))
        # extract() 提取数据 返回list
        img_src = img_div_list.extract()
        print(img_src)
        item['img_src'] = img_src

        # yield 就被管道接收了
        yield item
        # 接着发送请求爬取下一页数据
        if self.page <=5:
            self.page +=1
            url = self.url.format(self.page)
            # 发送请求
            yield scrapy.Request(url,callback=self.parse)

        # 使用css() ::attr(属性名)  获取标签属性  bs4中使用obj['属性名']
        # ret = response.css('选择器代码')
        print('-------------------------------------end--------------------------------------------')
        # 输出  scrapy crawl qiushibaike -o qiushibaike.json
        # _dict = {
        #     'name':'测试输出'
        # }
        # return _dict
