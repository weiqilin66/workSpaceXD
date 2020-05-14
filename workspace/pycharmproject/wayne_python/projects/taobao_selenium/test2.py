from selenium import webdriver
import time
path = r'd:/driverAndPlugs/chromedriver.exe'
"""selenium配合手动扫码测试成功"""
chrome = webdriver.Chrome(path)

url = 'https://s.taobao.com/'
chrome.get(url)



ser_input = chrome.find_elements_by_xpath("//input[@name='q']")[0]
ser_input.send_keys('宝可梦剑盾')
time.sleep(2)
ser_btn = chrome.find_elements_by_xpath("//button")[0]
ser_btn.click()

time.sleep(10)  # 手动扫码登录
# chrome.close()
html = chrome.page_source
print(html)