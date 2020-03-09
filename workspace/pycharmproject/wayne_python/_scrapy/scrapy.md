###爬虫框架是也
1. 核心内容 :引擎,下载器,spiders,调度器(schedule),管道(pipeline)
2. 使用流程
    1. items.py中定义数据结构
    2. spiders中创建文件爬取数据
    3. pipelines.py中操作数据存储数据
 
###使用
1. 创建项目 scrapy startproject 项目名称
2. 有__init__.py 是一个包 可导入
3. 目录结构
    1. items.py 定义数据结构文件
    2. middlewares.py 中间件
    3. pipeline.py 管道文件
    4. setting.py 配置文件
4. 修改配置 settings.py USER_AGENT修改为自己的UA 取消遵循robots协议False
5. 创建爬虫文件 进入项目目录 scrapy genspider 文件名 域名
6. 执行爬虫文件 进入spiders文件夹 scrapy crawl 文件名

###response属性和方法
1. text 字符串对象
2. body 字节
3. url
4. status
5. headers
6. xpath() 使用xpath 
    1. 获得的内容使用extract()获取selector对象的data内容 返回类型是string的list 单个为string 
    2. 方法返回的对象仍是response可以调用xpath 记得. 当前节点开始写xpath eg: ./a/img   
7. css() 根据选择器获取指定内容  参考bs4的层级选择器使用 标签名::attr(属性名)  获取标签属性  bs4中使用obj['属性名']
8. extract() 

###items
1. 在items.py中定义数据结构 
2. 这个对象比较特殊 使用方式是字典
3. yield item 
    1. yield 生成器  next()调用一次 
    2. 

###输出指定格式
1. 生成的文件目录下执行scrapy crawl spider文件名 -o 输出return的数据写入文件
    1. eg: scrapy crawl qiushibaike -o qiushibaike.json
    2. parse()方法:Spider must return Request, BaseItem, dict or None

###防盗链
1. 在yield item后面获取图片 scrapy.Request(url=img_url, callback=self.download) 在spider中定义一个下载方法
2. 原理: scrapy.Request 带有response的请求头

###爬取内容在多个链接网页中
1. 需要内容的链接    eg:详情页
2. 发送请求  yield scrapy.Request(url=详情页链接,callback=另一个response方法,meta={'key':value})
3. meta中传参
4. response.meta['key'] 来获取到Request传递的值

###crawlspider spider的一个子类 拥有强大的链接提取器功能
1. LinkExtractor(
    allow=正则表达式, # 提取符合正则表达式的链接
    deny=正则表达式, # 不要符合正则的链接
    restrict_xpaths = 符合的xpath的提取
    restrict_css = 符合的选择器提取
    deny_domains = 不允许的域名
)
2. 饭拍秀中所有图片详情页的链接,图片链接都可以使用这个提取
3. 实例: 
    0. 创建crawlspider   scrapy genspider -t crawl 文件名 域名        (多了-t crawl)
    1. links=LinkExtractor(allow=r'http://.*?move?page=\d')
    2. links.extract_link(response)  查看
    3. 单独使用 from scrapy.linkextractors import LinkExtractor


