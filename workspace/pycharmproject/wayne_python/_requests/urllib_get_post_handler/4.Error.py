import urllib.request
import urllib.error

# HTTPError 是URLError的子类

url = 'http://www.maodan.com/heiXio'

try:
    response = urllib.request.urlopen(url)
except urllib.error.HTTPError as e:
    print(e)
except urllib.error.URLError as e:
    print(e)