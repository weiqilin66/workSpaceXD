import os
import pymysql
from appium import webdriver
import time
from appium.webdriver.common.touch_action import TouchAction
from selenium.webdriver.support.wait import WebDriverWait

# adb API
"""
模拟点击
    adb shell input tap x y
"""
# 元素操作
"""
当无法定位元素时,使用高级手势去点击输入

获取当前应用包名界面名 
    adb shell dumpsys window windows | findstr mFocusedApp

元素定位 基于当前屏幕可见范围
    xpath: 
        文本: //*[@contains(@text,'包含文本')]
        id: resource-id(不唯一)
        class: ...
     调用java底层高级写法
    # driver.find_element_by_android_uiautomator('new UiSelector().className(\"android.widget.EditText\").textContains(\"最低价\")').send_keys(200)
    
元素等待()
    隐式:统一设置等待时间
        driver.implicitly_wait(10)
        后续所有找元素操作,都会10s时间等待元素出现,例:5s内出现了元素会立即执行该查找语句,第11s出现则报错(NoSuchElement...)
    显式:单独设置等待时间
        导包并创建对象wait = WebDriverWait(driver,5,1) 5s 每1秒都会去执行是否能找到元素,找不到报错(timeOut)
        使用 wait.until(lambda x:x.find_element_by_id("com.taobao.idlefish:id/right_btn"))    
    
元素位置和大小(返回dict) 左上角为基点
    element.location() key: x, y
    element.size() key: width, height

获取到元素后,获取元素属性
    element.get_attribute(属性名)  
    特殊获取
        class:className 
        resource-id:resourceId 
        content-desc: name
        text文本值: text 
"""
# 手势
"""
    滑动:
        swipe:
        (2个坐标的位置的移动,每次执行有小误差)
            start_x,start_y,    起点
            end_x,end_y,    终点
            duration    滑动时间ms,时间小滑动惯性越大展示效果越靠后
            driver.swipe(start_x ,start_y ,end_x, end_y ,duration)
            
        scroll:
        (2个元素之间的移动,有惯性误差,且无法指定惯性大小)
            driver.scroll(origin_element,target_element)
            
        drag_and_drop:
        (2个元素之间的移动,没惯性误差)
            driver.drag_and_drop(origin_element,target_element)
    
    TouchAction      
    (创建对象 act = TouchAction(driver)  perform()提交结尾 )  
        轻敲:
        (区别click 可以点击任何位置,和任意次数,不是双击)
            act.tap(element).perform()
            act.tap(x=100, y=100,count=3).perform() 坐标位置点击3次 默认点击1次
        双击:
            按下抬起+等待+按下抬起
        长按:
        (可以用按下抬起等待拼接,可以用专用方法long_press)
            act.long_press(坐标/元素,duration=时间ms).perform()
        移动:
        (按下再移动,例如手势解锁)
            act.press(...).move_to(坐标/元素).move_to(...).perform()
            
        按下抬起:
            act.press(坐标/元素).perform()  按下
            act.press(坐标/元素).release().perform()    抬起
        等待:
            act.wait(时间ms).perform()
            act.press(坐标/元素).wait(1000).release().perform() 按下1秒抬起 
"""
# 手机API
"""
截图
    driver.get_screenshot_as_file(存储路径)
呼出键盘
   # print('键盘列表: ',driver.available_ime_engines)
    # driver.activate_ime_engine(constants[phone]['输入法'])
    # driver.activate_ime_engine('io.appium.settings/.UnicodeIME')
    
按键
(driver.press_keycode())
    返回键: 4
    HOME: 3
    回车键: 23
复制黏贴
    
"""

phone = 'shark3'

# 弹窗坐标 以手机鼠标位置为准,坐标边缘可能大于分辨率 使用os.popen()来点击
constants = {
    # 黑鲨3
    'shark3': {
        '我的': (0.897, 0.957),
        '设置': (0.901, 0.04),
        '退出登录': (0.491, 0.951),
        '确定退出': (0.705, 0.558),
        '支付宝登录': (0.54, 0.66),
        '立即登录': (0.45, 0.396),
        '输入法': 'com.baidu.input_heisha/.ImeService',
        '最低价': (0.443, 0.438),
        '最高价': (0.796, 0.439),
        '筛选确定': 'adb shell input tap 790 2333',
        '双击间隔': 110,  # ms
        '上滑加载':(531, 2250, 540, 50),
        '启动三连':('adb shell input tap 541 1537','adb shell input tap 544 1342','adb shell input tap 555 1260')
    }
}

class MyAppium(object):
    def __init__(self, platformName, platformVersion, deviceName, appPackage, appActivity):
        # 前置代码(启动参数)
        self.desired_caps = dict()
        self.desired_caps['platformName'] = platformName  # 平台名 /IOS
        self.desired_caps['platformVersion'] = platformVersion  # 平台版本 可只写大版本 5
        self.desired_caps['deviceName'] = deviceName  # 设备名 可乱写
        self.desired_caps['appPackage'] = appPackage  # 包名
        self.desired_caps['appActivity'] = appActivity  # 界面名
        # 浮窗无法定位解决
        # self.desired_caps['automationName'] = 'uiautomator2'
        # 输入中文
        self.desired_caps['unicodeKeyboard'] = True
        self.desired_caps['resetKeyboard'] = True
        # 链接appium客户端
        self.driver = webdriver.Remote('http://localhost:4723/wd/hub', self.desired_caps)
        self.act = TouchAction(self.driver)
        # 分辨率 默认1080x1920
        self.default_x = self.driver.get_window_size()['width']
        self.default_y = self.driver.get_window_size()['height']
        print('当前屏幕分辨率: ', self.default_x, ' * ', self.default_y)

    # 根据屏幕百分比返回坐标 (tuple1: x,y为百分比参数 weditor可快速获得)
    def tapByPercentage(self, tuple1, count=1):
        res_x = tuple1[0] * self.default_x
        res_y = tuple1[1] * self.default_y
        self.act.tap(x=res_x, y=res_y, count=count).perform()

    def double_tap(self, tuple1):
        res_x = tuple1[0] * self.default_x
        res_y = tuple1[1] * self.default_y
        self.act.press(x=res_x, y=res_y).release().perform().wait(
            constants[phone]['双击间隔']).press(x=res_x, y=res_y).release().perform()

    # 获取当前包名 界面名
    def getCurrentPackageUI(self):
        print('包名:', self.driver.current_package, '     界面名:', self.driver.current_activity)
        return self.driver.current_package, self.driver.current_activity

    # app中启动其他app 例如支付宝 注意界面名不用加.
    def jump(self, package, ui):
        print('跳转应用:', package, '/', ui)
        self.driver.start_activity(package, ui)


# 数据层
class ToMysql(object):
    def __init__(self):
        self.conn = pymysql.connect('localhost', 'root', 'root', 'vhr')
        self.cursor = self.conn.cursor()

    def execute(self, sql, **kw):  # 关键字参数
        self.cursor.execute(sql, kw)
        self.conn.commit()

    def execute2(self, sql, *args):  # tuple参数
        self.cursor.execute(sql, args)
        self.conn.commit()


def initFish(app):
    driver = app.driver
    # 确定协议
    sure = WebDriverWait(driver, 10, 1).until(lambda x: x.find_elements_by_id("com.taobao.idlefish:id/right_btn"))
    if len(sure) != 0:
        sure[0].click()
    cmds = constants[phone]['启动三连']
    if cmds:
        for cmd in cmds:
            os.popen(cmd)
            time.sleep(0.8)
    # 判断是否登录,登录则重新登
    is_login = WebDriverWait(driver, 10, 1).until(
        lambda x: x.find_elements_by_xpath('//*[@resource-id="com.taobao.idlefish:id/login_guide_bar"]'))
    if len(is_login) == 0:
        print('已登录,准备退出登录')
        """
            等待有空改造使用元素获取
        """
        app.tapByPercentage(constants[phone]['我的'])
        time.sleep(1)
        app.tapByPercentage(constants[phone]['设置'])
        driver.swipe(510, 552, 524, 1550, 200)
        time.sleep(1)
        app.tapByPercentage(constants[phone]['退出登录'])
        time.sleep(1)
        app.tapByPercentage(constants[phone]['确定退出'])
        time.sleep(1)
    # 返回首页重新进入
    driver.press_keycode(3)
    time.sleep(1)


def login(app):
    app.jump('com.taobao.idlefish', 'com.taobao.fleamarket.home.activity.MainActivity')
    WebDriverWait(app.driver, 10, 1).until(
        lambda x: x.find_element_by_xpath('//*[@resource-id="com.taobao.idlefish:id/login_guide_bar"]')).click()
    time.sleep(1)
    app.tapByPercentage(constants[phone]['支付宝登录'])
    time.sleep(2)
    app.tapByPercentage(constants[phone]['立即登录'])
    WebDriverWait(app.driver, 10, 1).until(
        lambda x: x.find_element_by_id('com.taobao.idlefish:id/root_c_view')).click()


def crawl(app):
    driver = app.driver
    input_area = WebDriverWait(driver, 10, 1).until(
        lambda x: x.find_element_by_xpath('//*[@resource-id="com.taobao.idlefish:id/search_term"]'))
    input_area.send_keys('怪物猎人冰原')
    WebDriverWait(driver, 10, 1).until(
        lambda x: x.find_element_by_xpath('//*[@resource-id="com.taobao.idlefish:id/search_button"]')).click()
    time.sleep(2)
    # 筛选
    WebDriverWait(driver, 10, 1).until(lambda x: x.find_elements_by_xpath('//*[@text="筛选"]'))[0].click()
    time.sleep(1)
    app.tapByPercentage(constants[phone]['最低价'])
    time.sleep(0.5)
    driver.press_keycode(8)
    driver.press_keycode(8)
    driver.press_keycode(9)
    app.tapByPercentage(constants[phone]['最高价'])
    time.sleep(0.5)
    driver.press_keycode(8)
    driver.press_keycode(9)
    driver.press_keycode(10)
    # 分辨率之外的点击 os.popen('adb shell input tap x y ')
    os.popen(constants[phone]['筛选确定'])
    els = WebDriverWait(driver, 10, 1).until(lambda x: x.find_elements_by_xpath('//*[@class="android.view.View"]'))
    if len(els) > 0:
        for i in els:
            print(i.text)
    count =5
    # 下滑加载新元素
    while count>1:
        driver.swipe(constants[phone]['上滑加载'][0],constants[phone]['上滑加载'][1],constants[phone]['上滑加载'][2],constants[phone]['上滑加载'][3])
        time.sleep(0.5)
        els = WebDriverWait(driver, 10, 1).until(lambda x: x.find_elements_by_xpath('//*[@class="android.view.View"]'))
        if len(els) > 0:
            for i in els:
                print(i.text)
        count = count-1
        # 最后一个元素多个属性不同 判断加载结束
        
    time.sleep(20)



if __name__ == '__main__':
    # 雷电模拟器
    # app = MyAppium('Android', '5', 'emulator-5554', 'com.taobao.idlefish',
    #                'com.taobao.fleamarket.home.activity.MainActivity'
    #                )
    # 黑鲨3
    app = MyAppium('Android', '10', 'c45a530a', 'com.taobao.idlefish',
                   'com.taobao.fleamarket.home.activity.MainActivity'
                   )
    # 初始化 退出登录等
    initFish(app)
    # 登录
    login(app)
    # 爬取
    crawl(app)

    app.driver.close_app()
    app.driver.quit()
