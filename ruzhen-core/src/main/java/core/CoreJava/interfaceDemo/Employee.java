package core.CoreJava.interfaceDemo;

/**
 * 〈一句话功能简述〉<br>
 * 〈雇员〉
 *
 * @author lizhen
 * @create 2018/9/19
 * @since 1.0.0
 */
public class Employee  implements Comparable{
    private Double salary;

    @Override
    public int compareTo(Object o) {
        Employee other = (Employee) o;

        return Double.compare(salary, other.salary);
    }
}