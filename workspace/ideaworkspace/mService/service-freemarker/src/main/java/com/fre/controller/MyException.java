package com.fre.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: controllerAdvice 处理全局异常
 * @author: LinWeiQi
 */
@ControllerAdvice
public class MyException {
    /**
     * @TODO   当发生这个异常时,代替500返回给前台,也可以定制页面
     * @return void
     * @param  [e, res]
     * @date   2020/1/16
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public void fileException(MaxUploadSizeExceededException e , HttpServletResponse res) throws IOException {

        res.setContentType("text/html;charset=utf-8");
        PrintWriter writer = res.getWriter();
        writer.write("文件大小超过限制");
        writer.flush();
        writer.close();

    }
}
