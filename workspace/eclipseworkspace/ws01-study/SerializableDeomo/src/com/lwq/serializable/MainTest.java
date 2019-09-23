package com.lwq.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainTest {
	public static void main(String[] args) {
		Employee e = new Employee();
		e.setName("wayne");
		e.setNumber(111);
		try {
			FileOutputStream out = new FileOutputStream("test.ser");
			ObjectOutputStream o = new ObjectOutputStream(out);
			o.writeObject(e);
			out.close();
			o.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		FileInputStream ins;
		try {
			ins = new FileInputStream("test.ser");
			ObjectInputStream o2 = new ObjectInputStream(ins);
			Employee employee =(Employee) o2.readObject();
			System.out.println(e);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
