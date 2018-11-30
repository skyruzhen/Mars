package core.multiThreads.raceCondition;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈RequestID生成器〉
 *
 * @author lizhen
 * @create 2018/11/28
 * @since 1.0.0
 */
public class RequestIDGenerator{
    private final static RequestIDGenerator INSTANCE=new RequestIDGenerator();
    private final static short SEQ_UPPER_LIMIT=999;
    private short sequence = -1;

    public RequestIDGenerator() {
    }

    public short nextSequence(){
        if(sequence >= SEQ_UPPER_LIMIT){
            sequence=0;
        }else{
            sequence++;
        }
        return sequence;
    }

    public String nextID(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
        String timestamp=sdf.format(new Date());
        DecimalFormat df = new DecimalFormat("000");
        short sequenceNo=nextSequence();
        return "0049"+timestamp+df.format(sequenceNo);
    }

    public static RequestIDGenerator getInstance(){
        return INSTANCE;
    }

}