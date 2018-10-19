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
    private String name;
    private int year;
    private int month;
    private int day;

    public Employee(String name, Double salary,  int year, int month, int day) {
        this.salary = salary;
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public int compareTo(Object o) {
        Employee other = (Employee) o;

        return Double.compare(salary, other.salary);
    }
}