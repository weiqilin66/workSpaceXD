package com.readwrite.main;

import com.readwrite.entity.TableQ;
import com.readwrite.service.ReadExcelServiceImpl;
import com.readwrite.service.WriteVue;
import com.readwrite.utils.CnOrUs;
import com.readwrite.utils.ReadReplaceInsert;
import com.readwrite.utils.ToCamelFormat;


import java.io.*;
import java.util.Map;

public class APP {
    public static void main(String[] args)  {
        WriteVue.mvvm("e:/testArea/exmple.txt","e:/testArea/物理模型.xls","e:/testArea/exmple.txt",6);
    }


}