package core.CoreJava.textFile;

import core.CoreJava.interfaceDemo.Employee;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * 〈一句话功能简述〉<br>
 * 〈写入文件〉
 *
 * @author lizhen
 * @create 2018/10/19
 * @since 1.0.0
 */
public class TextFileTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000D, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 65000D, 1989, 10, 15);
        staff[2] = new Employee("Carl Cracker", 40000D, 1990, 4, 15);
        try(PrintWriter out = new PrintWriter("emloyee.dat",  "UTF-8")){
            writeData(staff, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void writeData(Employee[] employees, PrintWriter out){
        out.println(employees.length);
        for(Employee e: employees){
            writeEmployee(out, e);
        }
    }

    private static Employee[] readData(Scanner in){
        int n = in.nextInt();
        in.nextLine();
        Employee[] employees = new Employee[n];
        for(int i = 0; i < n; i++){
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    public static void writeEmployee(PrintWriter out, Employee e){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
    }

    public static Employee readEmployee(Scanner in){

        return null;
    }
}