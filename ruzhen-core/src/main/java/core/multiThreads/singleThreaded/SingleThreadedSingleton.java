package core.multiThreads.singleThreaded;

public class SingleThreadedSingleton {
    //保存该类的唯一实例
    private static SingleThreadedSingleton instance = null;

    //省略
    private SingleThreadedSingleton(){

    }

    public static SingleThreadedSingleton getInstance(){
        synchronized (SingleThreadedSingleton.class){
            if(null==instance){
                instance=new SingleThreadedSingleton();
            }
            return instance;
        }
    }

    public void someService(){

    }
}
