package core.CoreJava.bounce;

import com.thoughtworks.xstream.XStream;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/16
 * @since 1.0.0
 */
public class Ball {
    private static final int XSIZE = 15;
    private static final int YSIZE = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;
    private int life = 100;
    private boolean turn = new Random().nextBoolean();

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void move(Rectangle2D bounds){
        x += dx;
        if(turn){
            y += dy;
        }else{
            y -= dy;
        }

        if(x < bounds.getMinX()){
            x = bounds.getMinX();
            dx = -dx;
            life --;
            turn = new Random().nextBoolean();
        }
        if(x + XSIZE >= bounds.getMaxX()){
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
            life --;
            turn = new Random().nextBoolean();
        }
        if(y <bounds.getMinY()){
            y = bounds.getMinY();
            dy = -dy;
            life --;
            turn = new Random().nextBoolean();
        }
        if(y + YSIZE >= bounds.getMaxY()){
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
            life --;
            turn = new Random().nextBoolean();
        }
    }

    public Ellipse2D getShape(){
        return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
    }


}