package core.multiThreads.fileRenameTest;

import sun.font.TextRecord;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/11/28
 * @since 1.0.0
 */
public class FileRameTest {
    static Lock lock = new ReentrantLock();
    public static void main(String[] args) throws IOException, InterruptedException {

        File file = new File("D:/raw.txt");
        if(!file.exists()) {
            file.createNewFile();
        }

        Thread[] workers = new Thread[1000];
        for(int i= 0; i < 1000; i++){
            workers[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.lock();
                        FileInputStream inputStream = new FileInputStream(file);

                        FileOutputStream outputStream = new FileOutputStream(file);
                        outputStream.write(1111);
                        Thread.sleep(50);
                        outputStream.flush();
                        outputStream.close();
                        lock.unlock();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for(int i= 0; i < 1000; i++){
            workers[i].start();
        }

        //对文件命名
        Thread.sleep(10);
        file.renameTo(new File("D:/raw-copy.txt"));

        for(int i= 0; i < 1000; i++){
            workers[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.lock();
                        FileOutputStream outputStream = new FileOutputStream(file);
                        outputStream.write(1111);
                        Thread.sleep(50);
                        outputStream.flush();
                        outputStream.close();
                        lock.unlock();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for(int i= 0; i < 1000; i++){
            workers[i].start();
        }
    }
}