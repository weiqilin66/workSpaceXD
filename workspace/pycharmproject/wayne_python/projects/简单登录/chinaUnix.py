import urllib.request
import urllib.parse
import http.cookiejar
from selenium import webdriver
import json
# 思路 前台生成token传递 所以先试用selenium 获取到 html网页解析其中的token写入form中再发送请求

url = 'http://account.chinaunix.net/login/login'

headers={
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36'
}

cj = http.cookiejar.CookieJar()
handler2 =urllib.request.HTTPCookieProcessor(cj)
opener2 = urllib.request.build_opener(handler2)
_token = '2dG4jn9vjzyArSANEkt4T5MnyzH4BKq38nHB7ei5'
formData ={
    'username':'wayne45678',
    'password':'linweiqi45',
    '_token':_token,
    '_t':'1583485312036'
}
formData = urllib.parse.urlencode(formData).encode()
request = urllib.request.Request(url=url,headers=headers)
# resp = urllib.request.urlopen(request,data=formData).read().decode()
resp = opener2.open(request,data=formData).read().decode()
print(resp)
# 转换json格式
json_ret = json.loads(resp)
print(json_ret['data'])
resp2 = opener2.open(json_ret['data']['url']).read().decode()
print(resp2)

# fail 修改了token加密策略