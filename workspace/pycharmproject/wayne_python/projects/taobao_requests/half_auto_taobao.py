from selenium import webdriver
import time
import tkinter.messagebox  # 弹窗库
import re
import json
import pymysql
import random

""" 半自动实现 """


def start(random_good):
    chrome_path = r'd:/driverAndPlugs/chromedriver.exe'
    chrome = webdriver.Chrome(chrome_path)
    url = 'https://s.taobao.com/'
    chrome.get(url)
    chrome.maximize_window()  # 窗口最大化方便扫码
    ser_input = chrome.find_elements_by_xpath("//input[@name='q']")[0]
    ser_input.send_keys(random_good)
    time.sleep(2)
    ser_btn = chrome.find_elements_by_xpath("//button")[0]
    ser_btn.click()
    return chrome


def json2info(json_):
    json_dic = json.loads(json_)
    good_items = json_dic['mods']['itemlist']['data']['auctions']
    good_list = []
    for good_item in good_items:
        goods = {
            'shop': good_item['nick'],  # 店铺名
            'title': good_item['raw_title'],  # 宝贝标题
            'price': good_item['view_price'],  # 价格
            'sales': good_item['view_sales'],  # 销量
            'freight': good_item['view_fee'],  # 运费
        }
        good_list.append(goods)
    return good_list


def get_json_data(chrome, search_good):
    # 定义爬取的数据  只爬取按销量前两页
    # second_page_btn = chrome.find_elements_by_xpath("//ul/li[@class='item']/a[@aria-label='第2页']")

    # 刷新重新获取 input 和 搜索按钮
    tb_input = chrome.find_elements_by_xpath("//input[@name='q']")[0]
    tb_btn = chrome.find_elements_by_xpath("//button")[0]
    tb_input.clear()
    tb_input.send_keys(search_good)
    time.sleep(1)
    tb_btn.click()
    # 下拉到底部 一次到底考虑出滑块验证
    chrome.execute_script('window.scrollTo(0, document.body.scrollHeight)')
    # 拉取html
    html = chrome.page_source
    json_ = re.findall(r'g_page_config = (.*?)}};', html)[0]
    json_ = json_ + '}}'
    return json_


def info2mysql(good_list, conn, cursor,etl_date,etl_time):
    # 一个good_list是一个网页数据 统一设置一个时间

    for good in good_list:
        shop = good['shop']  # 店铺名
        title = good['title']  # 宝贝标题
        price = float(good['price'])  # 价格
        sales = good['sales']  # 销量
        freight = float(good['freight'])  # 运费
        # sql语句
        insert_sql = """
        insert into goods(shop,title,price,sales,freight,etl_date,etl_time) VALUES(%s,%s,%s,%s,%s,%s,%s)
        """
        # 执行插入数据到数据库操作
        cursor.execute(insert_sql, (shop, title, price, sales, freight, etl_date, etl_time))
        # 提交，不进行提交无法保存到数据库
        conn.commit()


def login(chrome):
    if chrome.current_url.startswith('https://login.taobao.com/'):
        tkinter.messagebox.showerror('提示','请及时扫码登录')
        time.sleep(10)
        login(chrome)
    else:
        tkinter.messagebox.showerror('提示','开始爬取!')


def main():
    search_goods = ['塞尔达传说', '宝可梦剑盾', '火焰纹章', '异度神剑']
    random_good = random.randint(0, len(search_goods) - 1)
    chrome = start(search_goods[random_good])
    # 15s手动扫码
    time.sleep(15)
    # https://login.taobao.com/ 如果未跳转到搜索页
    login(chrome)
    # 链接mysql
    conn = pymysql.connect('localhost', 'root', 'root', 'python')
    # 创建游标
    cursor = conn.cursor()
    # 数据日期
    etl_date = time.strftime("%Y-%m-%d", time.localtime())
    etl_time = time.strftime("%H:%M:%S", time.localtime())
    # 爬取数据
    for search_good in search_goods:
        json_ = get_json_data(chrome, search_good)
        # 随机请求间隔
        time.sleep(random.randint(2, 10))
        # 分析json
        good_list = json2info(json_)
        # 写入数据库
        info2mysql(good_list, conn, cursor,etl_date,etl_time)

    chrome.close()
    # 关闭游标和连接
    cursor.close()
    conn.close()


if __name__ == '__main__':
    main()
