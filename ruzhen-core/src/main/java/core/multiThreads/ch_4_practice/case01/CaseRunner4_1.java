package core.multiThreads.ch_4_practice.case01;

public class CaseRunner4_1 {
    public static void main(String[] args) throws Exception{
        if( 0== args.length){
            args = new String[]{
                    "https://www.microsoft.com/zh-cn/download/confirmation.aspx?id=27163",
                    "2",
                    "3"
            };
        }
        main0(args);
    }

    public static void main0(String[] args) throws Exception{
        final int argc = args.length;
        BigFileDownloader downloader = new BigFileDownloader(args[0]);

        //下载线程数
        int workerThreadCount = argc >= 2 ? Integer.valueOf(args[1]):2;
        long reportInterval = argc >= 3 ? Integer.valueOf(args[2]):2;

        System.out.printf("downloading %s%nConfig:workder threads:%s, reportInterval:%ss.",
                args[0], workerThreadCount, reportInterval);
        downloader.download(workerThreadCount, reportInterval);
    }
}
