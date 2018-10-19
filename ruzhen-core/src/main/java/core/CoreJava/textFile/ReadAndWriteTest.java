package core.CoreJava.textFile;

import java.io.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/19
 * @since 1.0.0
 */
public class ReadAndWriteTest {


    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));

        try {
            PrintWriter out = new PrintWriter("D:\\employee.txt");
            out.print("文本1");
            out.println();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) !=null){
            System.out.println(line);
        }
        in.close();

       /* InputStream in = null;
        try {
            in = new FileInputStream("D:/BugReport.txt");
            int available = in.available();
            if(available > 0){
                byte[] data = new byte[available];
                int size  = in.read(data);
                System.out.println(new String(data));
            }

            OutputStream out = new FileOutputStream("D:/BugReport.txt");
            byte[] data = { 0 , 0, 0, 0, 0, 0, 0, 1, 1, 1};

            out.write(data);
            System.out.println(new String(data, "UTF-8"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}