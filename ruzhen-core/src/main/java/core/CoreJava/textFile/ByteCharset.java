package core.CoreJava.textFile;

import sun.security.util.Length;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/22
 * @since 1.0.0
 */
public class ByteCharset {
    public static void main(String[] args) {
        Charset cset = Charset.forName("UTF-8");
        String str = "abc123中文";
        ByteBuffer buffer = cset.encode(str);
        byte[] bytes = buffer.array();
        System.out.println(Arrays.toString(bytes));

        ByteBuffer bbuf = ByteBuffer.wrap(bytes, 0, bytes.length);
        CharBuffer cbuf = cset.decode(bbuf);
        System.out.println(cbuf.toString());

        try {
            DataInputStream in = new DataInputStream(new FileInputStream("D:\\employee.dat"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}