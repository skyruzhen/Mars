package core.CoreJava.interfaceDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈接口与回调〉
 *
 * @author lizhen
 * @create 2018/9/19
 * @since 1.0.0
 */
public class TimerTest {

    public static void main(String[] args) {
        ActionListener listener = new TimePrinter();

        Timer t = new Timer(10000, System.out::println);
        t.start();

        JOptionPane.showMessageDialog(null, "退出程序？"); //lambda表达式
        System.exit(0);
    }
}

class TimePrinter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone, the time is " +LocalDate.now());

        Toolkit.getDefaultToolkit().beep();
    }
}