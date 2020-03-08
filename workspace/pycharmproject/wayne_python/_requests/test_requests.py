import requests

# 1. get
url = 'http://www.baidu.com'
resp = requests.get(url)
print(resp.encoding)
resp.encoding='utf8'
print(resp.text)
# 带参数get
url2= 'http://www.baidu.com/s'
data= {
    'ie':'utf8',
    'kw':'中国'
}
# 定制头部
headers = {
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36'
}
resp = requests.get(url,headers=headers,params=data)
# 字节写入 图片和视频必须如此
with open('baidu.html','wb') as pf:
    pf.write(resp.content)
# 2. post
# requests.post()

# 3. 代理
proxies_url = ''
proxies = {
    'http': proxies_url
}
# resp = requests.get(url,headers=headers,proxies=proxies)

# 4. cookie 往下的操作都带有cookie
s = requests.session()
s.get()
s.post()
# 会话统一的下载 不能使用urllib.request.urlretrieve(url,path)
img = s.get('img_src',headers=headers)
with open('img.png','wb') as pf:
    pf.write(img.content)
