package concurrent.chapter07;

public class TestCheckedExceptionInThread {
    public static void main(String[] args) {
        new Thread(()->{
            try {
                System.out.println(1/0);
            } catch (Exception e) {
                System.out.println("error occur");
                e.printStackTrace();
            }
        }).start();
    }
}
