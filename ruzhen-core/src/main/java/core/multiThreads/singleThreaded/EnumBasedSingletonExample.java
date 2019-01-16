package core.multiThreads.singleThreaded;

public class EnumBasedSingletonExample {
    public static void main(String[] args) {

        Thread t = new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };
        Thread t2 = new Thread(()-> {
            System.out.println(Singleton.class.getName());
            Singleton.INSTANCE.someService();
        });
    }

    public static enum Singleton{
        INSTANCE;
        //默认私有构造器
        Singleton(){
            System.out.println("Singleton inited.");
        }
        public void someService(){
            System.out.println("someService invoked.");
        }
    }
}
