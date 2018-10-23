package core.CoreJava.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/22
 * @since 1.0.0
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket s = new ServerSocket(8189)) {
            try (Socket incoming = s.accept()) {
                InputStream inputStream = incoming.getInputStream();
                OutputStream outputStream = incoming.getOutputStream();
                try (Scanner in = new Scanner(inputStream); Scanner response = new Scanner(System.in)) {
                    PrintWriter out = new PrintWriter(outputStream, true);
                    out.println("Hello! Enter Bye to exit.");
                    Thread resThread = new Thread(() -> {
                        while (response.hasNextLine()) {
                            String resStr = response.nextLine();
                            out.println("Echo: " + resStr);
                        }
                    });
                    resThread.start();
                    while (in.hasNextLine()) {
                        String line = in.nextLine();
                        System.out.println("Accept: " + line);
                    }
                    System.out.println("Exit!");
                } finally {
                    incoming.close();
                }

            }

        }
    }
}