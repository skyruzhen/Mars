package core.CoreJava.bounce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/16
 * @since 1.0.0
 */
public class Bounce {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new BounceFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class BounceFrame extends JFrame{
    private BallComponent comp;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;

    public BounceFrame(){
        setTitle("Bounce");
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", event-> {
                addBall();
        });
        addButton(buttonPanel, "Close", event->System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String title, ActionListener listener){
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall() {
        Ball ball = new Ball();
        comp.add(ball);
        Runnable r= ()->{
                try {
                    while(true) {
                        if (ball.getLife() > 0) {
                            ball.move(comp.getBounds());
                            comp.paint(comp.getGraphics());
                        }
                        System.out.println(  Thread.currentThread().getState());
                        Thread.sleep(DELAY);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                    e.printStackTrace();
                }
        };
        Thread t = new Thread(r);
        t.start();
    }

}