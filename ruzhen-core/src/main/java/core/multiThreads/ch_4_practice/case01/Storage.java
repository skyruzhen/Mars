package core.multiThreads.ch_4_practice.case01;

import core.util.Tools;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.RandomAccess;
import java.util.concurrent.atomic.AtomicLong;

public class Storage implements Closeable, AutoCloseable {
    private final RandomAccessFile storeFile;
    private final FileChannel storeChannel;
    protected final AtomicLong totalWrites = new AtomicLong(0);

    public Storage(long fileSize, String fileShortName) throws IOException {
        String fullFileName = System.getProperty("java.io.tmpdir")+"/"+fileShortName;
        String localFileName;
        localFileName = createStoreFile(fileSize, fullFileName);
        storeFile = new RandomAccessFile(localFileName, "rw");
        storeChannel = storeFile.getChannel();
    }

    /**
    * 将data中指定的数据写入文件
    * @param offset 写入数据在整个文件中的起始偏移位置
    * @param byteBuffer byteBuffer必须在该方法调用前执行byteBuffer.flip()
    * @throws IOException
    * @return 写入文件的数据长度
    * */
    public int store(long offset, ByteBuffer byteBuffer)throws IOException{
        int length;
        System.out.printf("offset:%s",offset);
        storeChannel.write(byteBuffer, offset);
        length = byteBuffer.limit();
        totalWrites.addAndGet(length);
        return length;
    }

    public long getTotalWrites(){return totalWrites.get();}

    private String createStoreFile(long fileSize, String fullFileName) throws FileNotFoundException {
        File file = new File(fullFileName);
        System.out.printf("create local file:%s\n", fullFileName);
        RandomAccessFile raf;
        raf = new RandomAccessFile(file, "rw");
        try {
            raf.setLength(fileSize);
        } catch (IOException e) {
            Tools.silentClose(raf);
        }finally {
            Tools.silentClose(raf);
        }
        return fullFileName;
    }

    @Override
    public synchronized void close() throws IOException {
        if(storeChannel.isOpen()){
            Tools.silentClose(storeChannel, storeFile);
        }
    }
}
