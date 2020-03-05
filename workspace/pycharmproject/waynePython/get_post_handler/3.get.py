import urllib.parse
import urllib.request

getUrl = ''

formData = {

}
# 不用转成字节 noEncode
formData = urllib.parse.urlencode(formData)
# get请求拼接url
getUrl += formData

headers ={

}
request = urllib.request.Request(getUrl, headers)

response = urllib.request.urlopen(request)

print(response.read().decode())