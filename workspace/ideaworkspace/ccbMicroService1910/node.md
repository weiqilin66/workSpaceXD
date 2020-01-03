####BUG
    1. SpringBoot项目启动后没进入Controller
            原因:
               当启动springboot时@SpringBootApplication注解会去自动扫描当前目录和其子目录，
               如果controller层不在子目录则扫描不到。所以将其配置到子目录中。
            解决:
            启动类放上级目录或者注解@SpringBootApplication(scanBasePackages = "com") 就会扫描com下目录
             
     2. zuul无响应,port 6开头无效,不知所以           
     
             