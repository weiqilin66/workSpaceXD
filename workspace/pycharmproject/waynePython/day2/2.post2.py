import urllib.request
import urllib.parse

postUrl = 'https://fanyi.baidu.com/v2transapi?from=en&to=zh'
# word = input("请输入要翻译的英语: ")
formData = {
    'from': 'en',
    'to': 'zh',
    'query': 'back',
    'transtype': 'translang',
    'simple_means_flag': '3',
    'sign': '561812.815525',
    'token': 'af9c62603e3c25823a05fc7d4f145ba5',
    'domain': 'common',
}
formData = urllib.parse.urlencode(formData).encode()
headers = {
    'Host': ' fanyi.baidu.com',
    'Connection': ' keep-alive',
    # 'Content-Length': ' 5', 不指定长度
    'Accept': '*/*',
    'X-Requested-With': ' XMLHttpRequest',
    'User-Agent': ' Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36',
    'Content-Type': ' application/x-www-form-urlencoded; charset=UTF-8',
    'Origin': ' https://fanyi.baidu.com',
    'Sec-Fetch-Site': 'same-origin',
    'Sec-Fetch-Mode': 'cors',
    'Referer': ' https://fanyi.baidu.com/?aldtype=16047',
    # 'Accept-Encoding': 'gzip, deflate, br',  不压缩
    'Accept-Language': ' zh-CN,zh;q=0.9,en;q=0.8',
    'Cookie': 'BIDUPSID=3C13857A532B1E7290DDF00FB4C724D6; PSTM=1577333408; BAIDUID=3C13857A532B1E72C6DCC3E7DBB983DF:FG=1; BDUSS=ZVV3NlM0dOellmdGNDQ1UtcTl5OTRPZ3dGamRZOUJRY1hXNX45bkVzd2dMaXhlSVFBQUFBJCQAAAAAAAAAAAEAAACVOHHGzuW4~LLQ1MJ5bwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACChBF4goQReek; to_lang_often=%5B%7B%22value%22%3A%22en%22%2C%22text%22%3A%22%u82F1%u8BED%22%7D%2C%7B%22value%22%3A%22zh%22%2C%22text%22%3A%22%u4E2D%u6587%22%7D%5D; REALTIME_TRANS_SWITCH=1; FANYI_WORD_SWITCH=1; SOUND_SPD_SWITCH=1; HISTORY_SWITCH=1; SOUND_PREFER_SWITCH=1; from_lang_often=%5B%7B%22value%22%3A%22zh%22%2C%22text%22%3A%22%u4E2D%u6587%22%7D%2C%7B%22value%22%3A%22en%22%2C%22text%22%3A%22%u82F1%u8BED%22%7D%5D; APPGUIDE_8_2_2=1; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; H_PS_PSSID=1424_21096_30824; Hm_lvt_64ecd82404c51e03dc91cb9e8c025574=1582087907,1582089786,1582099596,1582163347; Hm_lvt_eaa57ca47dacb4ad4f5a257001a3457c=1582087914,1582089792,1582099603,1582163355; yjs_js_security_passport=2b276281d13122492783fcde36c8f9425da54fbd_1582163364_js; delPer=0; PSINO=6; Hm_lpvt_64ecd82404c51e03dc91cb9e8c025574=1582163637; __yjsv5_shitong=1.0_7_3028e6b49a5332fb78634b21f97bde0177f6_300_1582163634800_211.97.128.74_8a2bdb59; Hm_lpvt_eaa57ca47dacb4ad4f5a257001a3457c=1582163645',
}


request = urllib.request.Request(postUrl, headers)

response = urllib.request.urlopen(request, formData)

print(response.read().decode())

# test fail 百度翻译加密了
