import urllib.request
import urllib.parse

# 请求的url
post_url = 'https://fanyi.baidu.com/sug'

# 请求的参数
word = input("请输入要翻译的英文: ")

form_data = {
    'kw': word
}

# 转码请求参数: encode()字符串=>字节,
#              decode()字节=>字符串
form_data = urllib.parse.urlencode(form_data).encode()

# 伪装客户端浏览器UA
headers = {
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36'
}

# 构建请求
# request = urllib.request.Request(url=post_url, headers=headers)
request = urllib.request.Request(post_url, headers)

# 发送请求
response = urllib.request.urlopen(request, data=form_data)

print(response.read().decode())

# test success!!!!
