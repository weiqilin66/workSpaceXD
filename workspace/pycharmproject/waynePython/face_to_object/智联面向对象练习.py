import urllib.parse
from bs4 import BeautifulSoup
import urllib.request
import json
import time

# 功能废弃 网站修改 数据由post接口返回 只做面向对象练习参考
class ZhiLianSpider(object):
    # https://sou.zhaopin.com/?p=2&jl=682&kw=python&kt=3
    url = 'https://sou.zhaopin.com/?'
    items=[]
    headers = {
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36'
    }

    def __init__(self, address, kw, page):  # 有参构造
        self.address = address
        self.kw = kw
        self.page = page

    # 通过 self访问属性 也可以类名.属性
    def run(self):
        # target_url = self.url +'?p=' + self.page +'&jl='+ self.address +'&kw'+ self.kw
        subData = {
            'p': self.page,
            'jl': self.address,
            'kw': self.kw
        }
        target_url = self.url + urllib.parse.urlencode(subData)
        print(target_url)
        # 请求
        request = urllib.request.Request(url=target_url, headers=self.headers)
        handler = urllib.request.HTTPHandler()
        opener = urllib.request.build_opener(handler)
        response = opener.open(request).read().decode()
        print(response)
        self.parse_content(response)
        time.sleep(2)  # 休息2秒

    # 数据整理逻辑 放入字典 dict
    def parse_content(self, response):
        soup = BeautifulSoup(response,'html.parser')
        table_list = soup.select('.contentpile__content__wrapper__item__info__box__jobname__title > table')
        for table in table_list:
            name = table.select('xxx')[0].text
            item ={
                '职位':name
            }
            self.items.append(item)
        # 写入
        string1 = json.dumps(self.items)  # dict转换成str 写入为二进制
        # string1 = json.dumps(self.items, ensure_ascii=False)  # 写中文

        with open('zhilian.txt',encoding='utf-8') as tf:
            tf.write(string1)
def main():
    address = input('请输入工作地点:')
    kw = input('请输入工作关键字:')
    start = int(input('请输入起始页:'))
    end = int(input('请输入结束页:'))
    for page in range(start, end + 1):
        # 创建对象 启动爬虫程序
        spider = ZhiLianSpider(address, kw, page)
        spider.run()


if __name__ == '__main__':
    main()

