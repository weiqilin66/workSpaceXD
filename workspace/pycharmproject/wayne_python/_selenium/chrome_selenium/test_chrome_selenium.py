from selenium import webdriver
# 导入chrome选项
from selenium.webdriver.chrome.options import Options
import time

# ****************配置驱动************************
path = r'd:/driverAndPlugs/chromedriver.exe'

# 创建chrome浏览器驱动，无头模式）
chrome_options = Options()
chrome_options.add_argument('--headless')
browser = webdriver.Chrome(executable_path=path, options=chrome_options)

# ****************开始操作浏览器********************

url = 'http://www.baidu.com/'
browser.get(url)
time.sleep(3)

# 打印页面标题 "百度一下，你就知道"
print(browser.title)

# 关闭浏览器
browser.quit()

# test fail
