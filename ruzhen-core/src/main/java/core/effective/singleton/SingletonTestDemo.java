package core.effective.singleton;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/23
 * @since 1.0.0
 */
public class SingletonTestDemo {
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();

        Elvis elvis2 = Elvis.INSTANCE;
        elvis2.leaveTheBuilding();
    }
}