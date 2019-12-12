import org.apache.juli.logging.LogFactory;

import javax.naming.ldap.Rdn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaoFu {
    public static void main(String[] args) {
        printFlower();
    }
    public  static void printFlower(){
        String flower ="";
        //flower = "*   *   *\n *  *  * \n  * * *\n   ***\n"+"    *\n"+"    *\n";
        
        System.out.println(flower);

    }
    public static int score(List<Integer> myBall,List<Integer> kBall) throws InterruptedException {
        int level = 0;
        int redCount = 0;
        int blueCount =0;
        List<Integer> red = new ArrayList<Integer>();
        List<Integer> blue = new ArrayList<Integer>();
        int index=1;
        for (int a:myBall) {
            if (index<=5){
                red.add(a);
            }else{
                blue.add(a);
            }
            index++;
        }
        List<Integer> kred = new ArrayList<Integer>();
        List<Integer> kblue = new ArrayList<Integer>();
        int index2=1;
        for (int a:kBall) {
            if (index2<=5){
                kred.add(a);
            }else{
                kblue.add(a);
            }
            index2++;
        }

       for(int i = 0;i<=kred.size()-1;i++) {
           for (int j = 0; j <= red.size()-1; j++)
               if (kred.get(i)==red.get(j)){
                   redCount++;
               }
       }
       for(int i = 0;i<=kblue.size()-1;i++) {
           for (int j = 0; j <= blue.size()-1; j++)
               if (kblue.get(i)==blue.get(j)){
                   blueCount++;
               }
       }




        if (myBall.toString().equals(kBall.toString())){
            //一等
            System.out.println("--------获得1等奖--------");
            level=1;
        }else if(redCount==4 &&blueCount==0){

            System.out.println("--------获得6等奖--------");
            Thread.sleep(1000);
            level=6;
        }else if(redCount==4 &&blueCount==1){
            System.out.println("--------获得5等奖--------");
            Thread.sleep(1000);
            level=5;
        }else if(redCount==4 &&blueCount==2){
            System.out.println("--------获得4等奖--------");
            Thread.sleep(1000);
            level=4;
        }else if(redCount==5 &&blueCount==0){
            System.out.println("--------获得3等奖--------");
            Thread.sleep(1000);
            level=3;
        }else if(redCount==5 &&blueCount==1){
            System.out.println("--------获得2等奖--------");
            Thread.sleep(1000);
            level=2;
        }
        return level;
    }
    public static List BlueAndRed(){
        List<Integer> result = new ArrayList<Integer>();

        List<Integer> red = new ArrayList<Integer>();
        List<Integer> blue = new ArrayList<Integer>();

        int[] redAll =  {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18
        ,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35};
        //int[] redAll = {1,2,3,4,5,6,7};
        int[] blueAll = {1,2,3,4,5,6,7,8,9,10,11,12};

        do{

            //--------获取红球
            int redIndex = (int) (Math.random()*redAll.length);
            int redBall = redAll[redIndex];
            if (!red.contains(redBall)){
                red.add(redBall);
                result.add(redBall);
            }
        }while (red.size()!=5 );
        do{
            //--------获取篮球
            int blueIndex = (int) (Math.random() * blueAll.length);
            int blueBall = blueAll[blueIndex];
            if (!blue.contains(blueBall)){
                blue.add(blueBall);
                result.add(blueBall);
            }
        }while (blue.size()!=2);

        return result;
    }

//    public static void main(String[] args) throws InterruptedException {
//        List<Integer> res =  new ArrayList<Integer>();
//        int[] restmp = {1,2,3,4,5,3,11};
//        for(int i =0;i<=restmp.length-1;i++){
//            res.add(restmp[i]);
//        }
//        List<Integer> x = new ArrayList<Integer>();
//        int level = 0;
//        int count =0;
///*        do{
//             x= BlueAndRed();
//            System.out.println("本期开奖--------号码:"+x);
//             count++;
//            System.out.println("count:"+count);
//        }while (!x.toString().equals(res.toString()));
//
//            System.out.println("恭喜中奖--------");
//            System.out.println("共买了"+ count+"期");*/
//        while (1==1){
//            x=BlueAndRed();
//            count++;
//            System.out.println("第"+count+"期开奖结果"+x);
//            level = score(res,x);
//            if (level==1){
//                break;
//            }
//        }
//
//    }

}
