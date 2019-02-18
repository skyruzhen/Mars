package concurrent.chapter10;

public class BootStrapClassLoader {
    public static void main(String[] args) {
        System.out.println("Bootstrap:"+String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path")); //根加载器路径
        System.out.println(System.getProperty("java.ext.dirs"));       //扩展类加载器介绍
        System.out.println(System.getProperty("java.class.path"));       //系统类加载器介绍
    }
}
