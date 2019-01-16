package core.multiThreads.publish;

public class FinalFieldExample {
    final int x;
    int y;
    static FinalFieldExample instance;
    public FinalFieldExample(){
        x=1;
        y=2;
    }
    public static void writer(){
        instance = new FinalFieldExample();
    }
    public static void reader(){
        final FinalFieldExample theInstance = instance;
        if(theInstance != null){
            int diff = theInstance.y - theInstance.x;
            System.out.println(diff);
        }
    }
}
