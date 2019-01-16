package core.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.Random;

public final class Tools {

    public static void silentClose(Closeable ... closeables){
        if(null == closeables){
            return;
        }
        for(Closeable c:closeables){
            if(null == c){
                continue;
            }
            try {
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void split(String str, String[] result, char delimeter) {
        int partsCount = result.length;
        int posOfDelimeter;
        int fromIndex = 0;
        String recordField;
        int i = 0;
        while(i < partsCount){
            posOfDelimeter = str.indexOf(delimeter, fromIndex);
            if( -1 == posOfDelimeter){
                recordField = str.substring(fromIndex);
                result[i] = recordField;
                break;
            }
            recordField = str.substring(fromIndex, posOfDelimeter);
            result[i] = recordField;
            i++;
            fromIndex = posOfDelimeter + 1;
        }
    }

    public static void randomPause(int ms) throws InterruptedException {
        Random random = new Random();
        int rms = random.nextInt(ms);
        Thread.sleep(rms);
    }
}
