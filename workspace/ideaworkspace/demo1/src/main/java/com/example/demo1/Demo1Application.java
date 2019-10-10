package com.example.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.io.File;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) throws Exception {
        System.out.println("args length:"+args.length);
        System.out.println("args0:"+args[0].toString());
        System.out.println("args1:"+args[1].toString());
        System.out.println("args2:"+args[2].toString());
        if( args==null||args.length<3){
            System.out.println("请输入操作参数");
            System.out.println("1:操作方法");
            System.out.println("2:下载的路径,upload时随意指定");
            System.out.println("3:上传路径,或者下载文件所在桶根路径");

        }
        String methodName = args[0].toString();
        String key = args[1].toString();
        String path = args[2].toString();
        File file = new File(path);
        if("upload".equals(methodName)) {
            COSUtil.getFiles(path);
        }
        if ("download".equals(methodName)){
            COSUtil.downLoadFile(key,file);
        }
        if ("fileCOS".equals(methodName)){
            COSUtil.fileCOS(key,file);
        }



        SpringApplication.run(Demo1Application.class, args);
    }


}
