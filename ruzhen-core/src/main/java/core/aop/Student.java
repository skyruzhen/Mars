package core.aop;

/**
 * 〈一句话功能简述〉<br>
 * 〈学生类〉
 *
 * @author lizhen
 * @create 2018/7/13
 * @since 1.0.0
 */
public class Student {
    private Integer age;
    private String name;

    public Integer getAge() {
        System.out.println("Age:"+age);
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        System.out.println("Name:"+name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printThrowException(){
        System.out.println("Exception raised");
        throw new IllegalArgumentException();
    }

}
