package core.effective.utilityclass;

/**
 * 〈一句话功能简述〉<br>
 * 〈不可被实例化〉
 *
 * @author lizhen
 * @create 2018/10/23
 * @since 1.0.0
 */
public class Utilityclass {
    //第4条：使用私有构造器来确保该类不能被实例化
    private Utilityclass(){
        throw new AssertionError();
    }
}