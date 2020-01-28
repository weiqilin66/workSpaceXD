package com.ud.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */
@RestController
public class UDController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * @param [file, req]
     * @return 返回文件地址
     * @TODO 单文件上传
     * @date 2020/1/16
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest req) {

        String dateFormat = sdf.format(new Date());
        //String realPath = req.getServletContext().getRealPath("/img/") + dateFormat;
        String realPath = "d:/img/"+dateFormat;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            file.transferTo(new File(folder, newName));//transferTo(new File(文件上传目录,文件名) )

//            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/img" + dateFormat + oldName;
            return realPath + newName;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }

}
