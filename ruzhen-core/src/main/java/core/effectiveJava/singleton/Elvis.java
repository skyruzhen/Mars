package core.effectiveJava.singleton;

/**
 * 〈一句话功能简述〉<br>
 * 〈Enum singleton - the perferred approach〉
 *
 * @author lizhen
 * @create 2018/6/15
 * @since 1.0.0
 */
public enum Elvis {
    INSTANCE;
    private  int i = 0;
    public void leaveTheBuilding(){
        // to do something
        i++;
        System.out.println("用枚举实现简洁的单例 i="+i);
    }
}
