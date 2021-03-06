package com.lwq.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeDemo
{
   public static void main(String [] args)
   {
      Employee e = new Employee();
      e.name = "pig";
      e.address = "tokey";
      e.SSN = 11122333;
      e.number = 101;
      //car类如果没序列化会报错
      e.car = new Car();
      e.car.setBrand("bmw");
      try
      {
         FileOutputStream fileOut =new FileOutputStream("employee.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(e);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/employee.ser");
      }catch(IOException i)
      {
          i.printStackTrace();
      }
   }
}