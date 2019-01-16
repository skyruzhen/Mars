package core.multiThreads.ch_4_practice.case01;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public class DownloadBuffer implements Closeable {
    /**
     * 当前Buffer中缓冲的数据相对于整个存储文件的位置偏移
     */
    private long globalOffset;
    private long upperBound;
    private int offset = 0;
    public final ByteBuffer byteBuffer;
    private final Storage storage;

    public DownloadBuffer(long globalOffset, long upperBound, final Storage storage){
        this.globalOffset = globalOffset;
        this.upperBound = upperBound;
        this.byteBuffer = ByteBuffer.allocate(1024*1024);
        this.storage = storage;
    }

    public void write(ByteBuffer buf) throws IOException{
        int length = buf.position();
        final int capacity = byteBuffer.capacity();
        //当前缓冲区已满，或者剩余容量不够容纳新数据
        if(offset+length>capacity || length == capacity){
            //将缓冲区的数据写入文件
            flush();
        }
        byteBuffer.position(offset);
        buf.flip();
        byteBuffer.put(buf);
        offset += length;
    }

    public void flush() throws IOException{
        int length;
        byteBuffer.flip();
        length = storage.store(globalOffset, byteBuffer);
        byteBuffer.clear();
        globalOffset += length;
        offset= 0;
    }

    public void close() throws IOException{
        System.out.printf("globalOffset:%s, upperBound:%s\n", globalOffset, upperBound);
        if(globalOffset < upperBound){
            flush();
        }
    }

}
