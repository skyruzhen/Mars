package concurrent.chapter07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class PreventDuplicated {
    private final static String LOCK_PATH = "D:/";
    private final static String LOCK_FILE=".lock";
    private final static String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException {
        //① 注入Hook线程，在程序退出时删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("The program received kill SIGNAL.");
            getLockFile().toFile().delete();
        }));

        //② 检查是否存在.lock文件
        checkRunning();

        //③ 简单模拟当前程序在运行
        for(;;){
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("program is running.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }

    private static void checkRunning() throws IOException{
        Path path = getLockFile();
        if(path.toFile().exists())
            throw new RuntimeException("The program already running.");

        //Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path);
    }

    private static Path getLockFile(){
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }

}
