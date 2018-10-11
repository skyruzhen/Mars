package core.CoreJava.GenericClass;

import java.io.File;
import java.util.Scanner;

/**
 * 〈一句话功能简述〉<br>
 * 〈测试Block〉
 *
 * @author lizhen
 * @create 2018/10/10
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        new Block(){

            @Override
            public void body() throws Exception {
                Scanner in = new Scanner(new File("ququx"), "UTF-8");
                while(in.hasNext()){
                    System.out.println(in.next());
                }
            }
        }.toThread().start();
    }
}