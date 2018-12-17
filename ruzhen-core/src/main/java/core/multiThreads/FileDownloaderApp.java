package core.multiThreads;

import core.util.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/11/26
 * @since 1.0.0
 */
public class FileDownloaderApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloader.class);
    public static void main(String[] args) {
        Thread downloaderThread=null;
        for(String url:args){
            //创建文件下载器线程
            downloaderThread=new Thread();
            //启动文件下载器线程
            downloaderThread = new Thread(new FileDownloader(url));
            // 启动文件下载器线程
            downloaderThread.start();
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
            String fileBaseName=fileURL.substring(fileURL.lastIndexOf('/')+ 1);
            try {
                URL url = new URL(fileURL);
                String localFileName = System.getProperty("java.io.tmpdir")+"/viscent-"+fileBaseName;
                System.out.println("Saving to: "+localFileName);
                downloadFile(url, new FileOutputStream(localFileName), 1024);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void downloadFile(URL url, OutputStream outputStream, int bufsize) throws IOException {
            //建立HTTP连接
            final HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            ReadableByteChannel inChannel = null;
            WritableByteChannel outChannel = null;
            try {
                httpConn.setRequestMethod("GET");

                int responseCode = httpConn.getResponseCode();
                if (2 != responseCode / 100) {
                    throw new IOException("Error: HTTP " + responseCode);
                }

                if (0 == httpConn.getContentLength()) {
                    LOGGER.debug("Nothing to be downloaded " + fileURL);
                }
                inChannel = Channels.newChannel(new BufferedInputStream(httpConn.getInputStream()));
                outChannel = Channels.newChannel(new BufferedOutputStream(outputStream));
                ByteBuffer buf = ByteBuffer.allocate(bufsize);
                while (-1 != inChannel.read(buf)) {
                    buf.flip();
                    outChannel.write(buf);
                    buf.clear();
                }
            }finally{
                //关闭指定的ChannelL以及HttpURLConnection
                Tools.silentClose(inChannel, outChannel);
                httpConn.disconnect();
            }
        }

    }
}