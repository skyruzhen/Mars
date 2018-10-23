package core.CoreJava.textFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/22
 * @since 1.0.0
 */
public class ZipInputStreamTest {
    public static void main(String[] args) throws IOException {
        ZipInputStream zin = null;
        try {
            zin = new ZipInputStream(new FileInputStream("D:\\employee.zip"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ZipEntry entry;
        while((entry = zin.getNextEntry())!= null){
            System.out.println(entry.getComment());
            System.out.println(entry.getName());
            System.out.println(entry.getMethod());
            zin.closeEntry();
        }

        Scanner in = new Scanner(zin);
        while (in.hasNextLine()){
            System.out.println(in.nextLine());
        }
        in.close();
        try {
            zin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}