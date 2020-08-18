from selenium import webdriver
"""
    selenium封装
"""

chrome_path = r'd:/driverAndPlugs/chromedriver.exe'

class MySelenium(object):
    def __init__(self):
        self.options = webdriver.ChromeOptions()
        self.options.add_experimental_option("prefs", {"profile.managed_default_content_settings.images": 2})
        self.options.add_experimental_option('excludeSwitches', ['enable-automation'])
        self.chrome = webdriver.Chrome(executable_path=chrome_path, options=self.options)