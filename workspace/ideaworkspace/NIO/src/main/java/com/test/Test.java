package com.test;

import com.utils.SearchPosition;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Test {
    public static void main(String[] args) throws IOException {
        File file = new File("src/nomal_io.txt");

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        long i = SearchPosition.exactPos(raf,"林伟奇");
        System.out.println("pos="+i);
        /*
        List<String> keywordList = new ArrayList<String>();
        keywordList.add("林");
        Map<Integer,String> linePosMap  = position(raf,keywordList,file);

        for (Map.Entry<Integer,String> entry:linePosMap.entrySet()
        ) {
            System.out.println(entry.getKey()+","+entry.getValue());
        }
    }*/
    }
}
