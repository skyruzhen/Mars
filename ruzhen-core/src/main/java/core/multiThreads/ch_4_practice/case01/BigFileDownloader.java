package core.multiThreads.ch_4_practice.case01;

import core.util.Tools;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

public class BigFileDownloader {
    protected final URL requestURL;
    protected final long fileSize;

    //负责已下载数据的存储
    protected final Storage storage;
    protected final AtomicBoolean taskCanceled = new AtomicBoolean(false);

    public AtomicBoolean getTaskCanceled() {
        return taskCanceled;
    }

    public BigFileDownloader(String strURL) throws Exception{
        requestURL = new URL(strURL);

        //获取待下载资源的大小（单位：字节）
        fileSize = retieveFileSize(requestURL);
        String fileName = strURL.substring(strURL.lastIndexOf('/')+1);
        //创建负责存储已下载数据的对象
        storage = new Storage(fileSize, fileName);
    }
    /**
     *下载指定的文件
     * @param taskCount 任务个数
     * @param reportInterval 下载进度报告周期
     * @throws Exception
    */
    public void download(int taskCount, long reportInterval) throws InterruptedException {
        long chunkSizePerTHread = fileSize/taskCount; // 138=277/2;
        //下载数据段的起始字节
        long lowerBound = 0;
        //下载数据段的结束字节
        long upperBound = 0;

        DownloadTask dt;
        for(int i = taskCount -1; i >= 0; i--){
            /*
            * 线程1： lowerBound = 1*138;
            *         upperBound =277;
            * 线程2： lowerBound = 0;
            *         upperBound = 0+138-1 = 137;
            */
            lowerBound = i*chunkSizePerTHread;
            if(i==taskCount -1){
                upperBound = fileSize;
            }else{
                upperBound = lowerBound + chunkSizePerTHread -1;
            }
            //创建下载任务
            dt = new DownloadTask(lowerBound, upperBound, requestURL, storage, taskCanceled);
            dispatchWork(dt, i);
        }
        // 定时报告下载进度
        reportProgress(reportInterval);
        // 清理程序占用的资源
        doCleanup();
    }

    private void doCleanup() {
        Tools.silentClose(storage);
    }

    // 报告下载进度
    private void reportProgress(long reportInterval) throws InterruptedException {
        float lastCompletion;
        int completion = 0;
        while (!taskCanceled.get()) {
            lastCompletion = completion;
            completion = (int) (storage.getTotalWrites() * 100 / fileSize);
            if (completion == 100) {
                break;
            } else if (completion - lastCompletion >= 1) {
                System.out.printf("Completion:%s%%\n", completion);
                if (completion >= 90) {
                    reportInterval = 1000;
                }
            }
            Thread.sleep(reportInterval);
        }
        System.out.printf("Completion:%s%%\n", completion);
    }
    private void dispatchWork(DownloadTask dt, int workderIndex) {
        //创建下载线程
        Thread workerThread = new Thread(()->{
            try{
                dt.run();
            }catch (Exception e){
                e.printStackTrace();
                //取消整个文件的下载
                cancleDownload();
            }
        });
        workerThread.setName("downloader-"+workderIndex);
        workerThread.start();
    }

    private void cancleDownload() {
        if(taskCanceled.compareAndSet(false, true)){
            doCleanup();
        }
    }

    /**
     *根据指定的URL获取相应文件的大小
     * @param requestURL
     * @return
     */
    private long retieveFileSize(URL requestURL) throws Exception {
        long size = -1;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) requestURL.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int statusCode =connection.getResponseCode();
            System.out.println("返回码："+statusCode);
            if(HttpURLConnection.HTTP_OK != statusCode){
                throw new Exception("Server exception, status code:"+statusCode);
            }
            String cl = connection.getHeaderField("Content-Length");
            size = Long.valueOf(cl);
            System.out.println("连接"+requestURL.getPath()+"成功！ 文件大小："+size);
        } catch (IOException e) {
           if(null != connection){
               connection.disconnect();
           }
        }finally {
            connection.disconnect();
        }
      return size;
    }
}
