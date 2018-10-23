package core.CoreJava.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 〈一句话功能简述〉<br>
 * 〈网络程序〉
 *
 * @author lizhen
 * @create 2018/10/22
 * @since 1.0.0
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        try(Socket s = new Socket()){
            //s.connect(new InetSocketAddress("www.baidu.com", 13), 60);

            InetAddress address = InetAddress.getByName("www.baidu.com");
            System.out.println(address);
            Charset cset = Charset.forName("UTF-8");
            byte[] bytes = address.getAddress();
            ByteBuffer bbuf = ByteBuffer.wrap(bytes, 0, bytes.length);
            CharBuffer cbuf = cset.decode(bbuf);
            System.out.println(cbuf.toString());
            System.out.println(Arrays.toString(bytes));
         /*   InputStream inStream = s.getInputStream();
            Scanner in = new Scanner(inStream);
            while(in.hasNextLine()){
                System.out.println(in.nextLine());
            }*/
        }
    }
}