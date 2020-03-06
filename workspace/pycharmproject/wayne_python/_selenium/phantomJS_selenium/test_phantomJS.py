from selenium import webdriver
import time

# selenium 不支持了 降低版本 pip install selenium==2.48.0

# phantomJS路径
phantom_path = 'd:/driverAndPlugs/PhantomJS/bin/phantomjs.exe'
browser = webdriver.PhantomJS(phantom_path)

# 打开百度
url = 'http://wwww.baidu.com'
browser.get(url)

# 拍照
# browser.save_screenshot('phantom_screenshot/baidu.png')

# 执行滚动条   执行js是phantomJS的核心功能
js = 'document.body.scrollTop=10000'
browser.execute_script(js)

# 获取网页的代码
html = browser.page_source

# with open('html/baidu.html','w',encoding='utf8') as fp:
#     fp.write(html)

browser.quit()
