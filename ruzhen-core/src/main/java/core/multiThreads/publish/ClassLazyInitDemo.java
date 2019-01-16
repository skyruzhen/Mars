package core.multiThreads.publish;

public class ClassLazyInitDemo {

    public static void main(String[] args) {
        System.out.println(Collaborator.class.hashCode());
        System.out.println(Collaborator.number);
        System.out.println(Collaborator.flag);
    }

    static class Collaborator{
        static int number = 1;
        static boolean flag = true;
        static{
            System.out.println("Collaborator initializing...");
        }
    }
}
