# -*- coding: utf-8 -*-
import json

# Define your item pipelines here
"""通道 写数据 需开启配置文件 67 68 69 行"""


class TestScrapyPipeline(object):

    # 重写方法 ,当爬虫开启时候就会调用这个方法
    def open_spider(self, spider):
        # 加self 使 fp从局部变量变成成员变量
        self.fp = open('qiushibaike.txt', 'a', encoding='utf8')

    # 当爬虫结束掉用这个方法 ,
    def close_spider(self, spider):
        self.fp.close()

    # 处理item数据的函数  yield一次item 就会调用一次
    def process_item(self, item, spider):
        # 将item保存到文件中
        # item 是字典用法的obj 转换成dict
        dic = dict(item)
        s = json.dumps(dic, ensure_ascii=False)
        self.fp.write(s)
        return item
