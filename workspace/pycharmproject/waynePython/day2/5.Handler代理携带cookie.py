import urllib.request
import http.cookiejar


url = 'http://www.baidu.com'
headers = {
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36'
}
# 1.创建handler
handler = urllib.request.HTTPHandler()
# 2.通过handler创建opener
opener = urllib.request.build_opener(handler)
# 3.opener发送请求
request= urllib.request.Request(url,headers=headers)
resp = opener.open(request)
print(resp.read().decode())


# 携带cookie的handler
# 1.创建cookiejar
cj = http.cookiejar.CookieJar()
handler2 =urllib.request.HTTPCookieProcessor(cj)
opener2 = urllib.request.build_opener(handler2)
# opener2发送请求后cookie自动保存到opener2中



