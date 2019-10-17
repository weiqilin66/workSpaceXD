import org.omg.Messaging.SyncScopeHelper;

import java.util.ArrayList;
import java.util.List;

public class TestNullAndNew {
    public static void main(String[] args) {
        method2();
    }
    //method1报错是由于声明list时只声明了一个引用,并没有开辟内存空间
    public static void method1(){
        List list = null; //等价于 List list; 的写法
        list.add(1);
        for (Object a:list
        ) {
            System.out.println(a);
        }
    }
    public static void method2(){
        List list = new ArrayList();
        list.add(1);
        for (Object a:list
        ) {
            System.out.println(a);
        }
    }
}
