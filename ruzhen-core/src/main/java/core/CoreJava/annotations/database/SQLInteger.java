package core.CoreJava.annotations.database;

public @interface SQLInteger {
    String name() default "";
    Constraints constraints() default  @Constraints;
}
