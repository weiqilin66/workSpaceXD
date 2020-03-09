import requests
import re

headers = {
    'referer': 'https://www.taobao.com/',
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36'
}
resp = requests.get(url='https://s.taobao.com/search?ie=utf8&initiative_id=staobaoz_20200309&stats_click=search_radio_all%3A1&js=1&imgfile=&q=%E5%AE%9D%E5%8F%AF%E6%A2%A6%E5%89%91%E7%9B%BE&suggest=history_1&_input_charset=utf-8&wq=%E5%AE%9D%E5%8F%AF%E6%A2%A6&suggest_query=%E5%AE%9D%E5%8F%AF%E6%A2%A6&source=suggest'
                    ,headers=headers)
print(resp.text)
print('-' * 50)
with open('t1.html','w') as fp:
    fp.write(resp.text)
print(re.search(r'g_page_config = (.*?)}};', resp.text))

"""测试失败 搜索接口也要求登录"""