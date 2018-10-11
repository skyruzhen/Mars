package core.CoreJava.GenericClass;

/**
 * 〈一句话功能简述〉<br>
 * 〈消除对受查异常的检查〉
 *
 * @author lizhen
 * @create 2018/10/10
 * @since 1.0.0
 */
public abstract class Block {

    public abstract void body() throws Exception;

    public Thread toThread(){
        return new Thread(){
            public void run(){
                try {
                    body();
                } catch (Throwable t) {
                    Block.<RuntimeException>throwAs(t);
                }
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> void throwAs(Throwable e) throws T{
        throw (T) e;
    }
}