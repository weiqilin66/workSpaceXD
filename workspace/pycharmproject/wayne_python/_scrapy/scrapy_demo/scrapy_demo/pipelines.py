# -*- coding: utf-8 -*-

import json
import urllib.request
import os
import pymysql


# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


class ScrapyDemoPipeline(object):
    def __init__(self):
        self.fp = open('校花.txt', 'a', encoding='utf8')
        self.img_path = 'd:/img/'
        # 链接mysql
        self.conn = pymysql.connect('localhost', 'root', 'root', 'python')  # 有中文要存入数据库的话要加charset='utf8'
        # 创建游标
        self.cursor = self.conn.cursor()

    def close_spider(self, spider):
        self.fp.close()
        # 关闭游标和连接
        self.cursor.close()
        self.conn.close()

    def process_item(self, item, spider):
        dic = dict(item)
        s = json.dumps(dic, ensure_ascii=False)
        self.fp.write(s)
        # 存入数据库
        # sql语句
        insert_sql = """
        insert into img_table(name,img_url) VALUES(%s,%s)
        """
        # 执行插入数据到数据库操作
        self.cursor.execute(insert_sql, (item['name'],item['img_url']))
        # 提交，不进行提交无法保存到数据库
        self.conn.commit()

        # 下载图片
        self.download_img(dic)
        return item

    def download_img(self, dic):
        path = os.path.join(self.img_path, dic['name'] + '.jpg')
        print('下载%s' % path)
        urllib.request.urlretrieve(dic['img_url'], path)
