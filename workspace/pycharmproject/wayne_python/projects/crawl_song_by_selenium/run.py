import os
import random
import time
import tkinter.messagebox  # 弹窗库

from _auto_projects.my_service.my_selenium import MySelenium

"""
**
"""


class operateDirAndFile(object):
    def __init__(self):
        pass

    def file_name(self, file_dir):
        for root, dirs, files in os.walk(file_dir):
            print(root)  # 当前目录路径
            print(dirs)  # 当前路径下所有子目录
            print(files)  # 当前路径下所有非目录子文件
        return root, dirs, files


def writeArticle(file_name, article):
    print(article)
    # 创建文件夹
    dirName = 'song_article'
    if not os.path.exists(dirName):
        os.mkdir(dirName)
    if file_name=='':
        file_name=random.randint(1,1000)
    filePath = dirName + '/' + str(file_name)
    with open(filePath, 'w', encoding='utf8') as fp:
        fp.write(article)


def get_article(hrefs):
    for href in hrefs:
        print(href)
        driver.get(href)
        try:
            node_text = ''
            article_text = ''
            title = driver.find_element_by_xpath("//div[@class='card'][1]//h1").text
            if title=='':
                time.sleep(15)
                title = driver.find_element_by_xpath("//div[@class='card'][1]//h1").text
            nodes = driver.find_elements_by_xpath("//div[@class='card'][1]//h2")
            for node in nodes:
                node_text = node_text + node.text + '\n'
            p_list = driver.find_elements_by_xpath("//div[@class='card'][1]//p")
            for p in p_list:
                article_text = article_text + p.text + '\n'
            file_name = title + '.txt'
            root, dirs, files = operateDirAndFile().file_name('song_article')
            flag=0
            for file in files:
                if file == file_name:
                    flag = flag+1

            if flag != 0:
                continue
            writeArticle(file_name, title + '\n' + node_text + '\n' + article_text)
        except Exception as e:
            print(e)
            tkinter.messagebox.showinfo('凸(艹皿艹 )', '抛马勒戈壁的异常了')  # 手动滑块


if __name__ == '__main__':
    driver = MySelenium().chrome
    driver.implicitly_wait(20)
    url = "http://www.javaboy.org/"
    driver.get(url)
    # http://www.javaboy.org/page/3/ 第三页

    alist = driver.find_elements_by_xpath("//div[@class='card']//h1//a")
    hrefs = []
    for a in alist:  # 当前页文章地址
        target_url = a.get_attribute('href')
        hrefs.append(target_url)
    get_article(hrefs)
    # 翻页
    for i in range(2, 1000):
        print('开始爬取第%s页' % str(i))
        url = 'http://www.javaboy.org/page/' + str(i) + '/'
        driver.get(url)
        alist = driver.find_elements_by_xpath("//div[@class='card']//h1//a")
        if len(alist) == 0:
            print('-----' * 200, '\n', '爬取到第%s页后结束' % str(i))
            driver.quit()
        hrefs = []
        for a in alist:  # 当前页文章地址
            target_url = a.get_attribute('href')
            hrefs.append(target_url)
        get_article(hrefs)

    driver.quit()
