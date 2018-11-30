package core.multiThreads;

import ch.qos.logback.core.net.SyslogOutputStream;
import sun.security.util.Debug;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/11/26
 * @since 1.0.0
 */
public class FileDownloaderApp {
    public static void main(String[] args) {
        Thread downloaderThread=null;
        for(String url:args){
            //创建文件下载器线程
            downloaderThread=new Thread();
            //启动文件下载器线程

        }
    }

    static class FileDownloader implements Runnable{
        private final String fileURL;

        public FileDownloader(String fileURL){
            this.fileURL=fileURL;
        }

        @Override
        public void run() {
            System.out.println("Downloading from "+fileURL);
            String fileBaseName=fileURL.substring(fileURL.lastIndexOf('/'), 1);
            try {
                URL url = new URL(fileURL);
                String localFileName = System.getProperty("java.io.tmpdir")+"/viscent-"+fileBaseName;
                System.out.println("Saving to: "+localFileName);

                //TODO 下载文件
                downloadFile(url, new FileOutputStream(localFileName), 1024);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void downloadFile(URL url, OutputStream outputStream, int bufsize){
            //
        }
    }
}