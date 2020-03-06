from selenium import webdriver
import time

chrome_path = 'd:/driverAndPlugs/chromedriver.exe'
browser = webdriver.Chrome(chrome_path)

# url = 'https://tieba.baidu.com/index.html'
url ='http://www.baidu.com'
browser.get(url)

# 根据id查找元素
my_input = browser.find_element_by_id('kw')
# 输入框输入内容
my_input.send_keys('美女')
time.sleep(3)

# 根据类名搜索元素
search_btn = browser.find_elements_by_class_name('s_btn')
if len(search_btn)>0:
    # 点击
    search_btn[0].click()
else:
    print('未找到按钮')


time.sleep(3)
# 关闭
browser.quit()
