package core.effectiveJava.enumDemo;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/25
 * @since 1.0.0
 */
public enum Planet {
    MERCURY(3.302e+23, 2.439e6),
    MARS(6.419e+29, 3.393e6);
    private final double mass;
    private final double radius;
    Planet(double mass, double radius){
        this.mass = mass;
        this.radius =radius;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }
}