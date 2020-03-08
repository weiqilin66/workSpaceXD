import urllib.request
import urllib.parse
import http.cookiejar
from selenium import webdriver
import json
# 思路 前台生成token传递 所以先get请求页面 获取到 html网页解析其中的token写入form中再发送请求

url = 'http://account.chinaunix.net/login/login'

headers={
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36'
}

cj = http.cookiejar.CookieJar()
handler2 =urllib.request.HTTPCookieProcessor(cj)
opener2 = urllib.request.build_opener(handler2)
_token = 'q9oLcERoEakbuUt8paFEF2aQOkuDdbUvRgbIRSn1'
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
print(json_ret['data']['url'])
# 登录post请求前发送了get请求
url2 = 'http://account.itpub.net/login/sign?token=9d0bd5a79710cb14a01c8fa2086782f6 '
url3 = 'http://account.wenku.it168.com/login/sign?token=9d0bd5a79710cb14a01c8fa2086782f6'
request2 = urllib.request.Request(url2,headers=headers)
opener2.open(request2)
request3= urllib.request.Request(url3,headers=headers)
opener2.open(request3)
# 登录post请求
resp2 = opener2.open(json_ret['data']['url']).read().decode()
print(resp2)

# fail 修改了token加密策略