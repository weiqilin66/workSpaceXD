from lxml import etree
from selenium import webdriver
import requests
from selenium.webdriver.chrome.options import Options


url = 'https://tieba.baidu.com/index.html'
chrome_path = 'd:/driverAndPlugs/chromedriver.exe'

# 创建chrome浏览器驱动，无头模式
chrome_options = Options()
chrome_options.add_argument('--headless')
chrome_options.add_argument('--disable-gpu')
browser = webdriver.Chrome(executable_path=chrome_path, chrome_options=chrome_options)
