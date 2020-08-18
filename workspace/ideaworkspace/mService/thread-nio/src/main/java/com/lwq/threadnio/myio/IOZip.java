package com.lwq.threadnio.myio;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 文件打包
 * @author: LinWeiQi
 */
@Service
public class IOZip {


    public ResponseEntity<Byte[]> doZip(HttpServletResponse response){
        ServletOutputStream outputStream = null;
        String downloadName = "xxx附件.zip";
//        try {
//            response.setContentType("multipart/form-data");
//            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(downloadName, "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            outputStream = response.getOutputStream();
//            ZipOutputStream zos = new ZipOutputStream(outputStream);//?
//            ZipEntry zipEntry = new ZipEntry();
//            zos.putNextEntry();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return null;
    }
}
