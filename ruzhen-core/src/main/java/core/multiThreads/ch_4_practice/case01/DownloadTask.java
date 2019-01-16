package core.multiThreads.ch_4_practice.case01;

import core.util.Tools;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 下载子任务
 */
public class DownloadTask implements Runnable{
    private final long lowerBound;
    private final long upperBound;
    private final DownloadBuffer xbuf;
    private final URL requestURL;
    private final AtomicBoolean canceFlag;

    public DownloadTask(long lowerBound, long upperBound, URL requestURL, Storage storage, AtomicBoolean canceFlag) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.requestURL = requestURL;
        this.xbuf = new DownloadBuffer(lowerBound,upperBound,storage);
        this.canceFlag = canceFlag;
    }

    //对指定的URL发起HTTP分段下载请求
    private static InputStream issueRequest(URL requestURL, long lowerBound, long upperBound)throws IOException{
        Thread me = Thread.currentThread();
        System.out.println(me+"->["+lowerBound+","+upperBound+"]");
        final HttpURLConnection connection;
        InputStream in = null;
        connection = (HttpURLConnection) requestURL.openConnection();
        String strConnTimeout = System.getProperty("x.dt.conn.timeout");
        int connTimeout = null == strConnTimeout ? 60000:Integer.valueOf(strConnTimeout);
        connection.setConnectTimeout(connTimeout);
        String strReadTimeout = System.getProperty("x.dt.read.timeout");
        int readTimeout = null == strReadTimeout?60000:Integer.valueOf(strReadTimeout);
        connection.setReadTimeout(readTimeout);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Connection", "Keep-alive");
        //Range:bytes=0-1024
        connection.setRequestProperty("Range", "bytes="+lowerBound+"-"+upperBound);
        connection.setDoInput(true);
        connection.connect();

        int statusCode = connection.getResponseCode();
        if(HttpURLConnection.HTTP_PARTIAL != statusCode){
            connection.disconnect();
            throw new IOException("Server exception, status code:"+statusCode);
        }
        System.out.printf(me+"-Content-Range:"+connection.getHeaderField("Content-Range")
        +",connection:"+connection.getHeaderField("connection"));

        in = new BufferedInputStream(connection.getInputStream()){
            @Override
            public void close() throws IOException {
                try {
                    super.close();
                } finally {
                    connection.disconnect();
                }
            }
        };
        return in;
    }

    @Override
    public void run() {
        if(canceFlag.get()){
            return;
        }
        ReadableByteChannel channel = null;
        try {
            channel = Channels.newChannel(issueRequest(requestURL, lowerBound, upperBound));
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (!canceFlag.get() && channel.read(buf) > 0){
                //将从网络读取的数据写入缓冲区
                xbuf.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            Tools.silentClose(channel, xbuf);
        }
    }
}
