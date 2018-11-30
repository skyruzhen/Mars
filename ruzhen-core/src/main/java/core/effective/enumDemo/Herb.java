package core.effective.enumDemo;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/25
 * @since 1.0.0
 */
public class Herb {
    public enum Type{ANNUAL, PREENNIAL, BIENNIAL}
    private final String name;
    public final Type type;
    Herb(String name, Type type){
        this.name = name;
        this.type = type;
    }
    @Override public String toString(){
        return name;
    }
}