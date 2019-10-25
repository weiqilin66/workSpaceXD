package com.readwrite.service;

import sun.rmi.transport.ObjectTable;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

public class WriteJSON {

    public String writeJ(Map<Integer, Object> map,String targetPath)throws IOException {
        String str ="";






        File file = new File(targetPath);
        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        long index = raf.length();
        raf.seek(index);
        raf.write(str.getBytes());
        String JsonStr = null;




        return JsonStr;
    }
}
