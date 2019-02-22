package core.CoreJava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 看起来很像接口，事实上和其他任何接口一样，注解也将会编译成class文件
 */
@Target(ElementType.METHOD)  //注解用于什么地方，方法或者一个域？
@Retention(RetentionPolicy.RUNTIME) //哪一个级别可用？ 源代码（SOURCE)、类文件中（CLASS)或者运行时（RUNTIME)
public @interface Test {}
