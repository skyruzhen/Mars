package core.CoreJava.annotations;


/**
 * 注解使得我们能够以将由编译器来测试和验证的格式，存储有关程序的额外信息。注解可以用来生成描述符文件，甚至或是新的类定义
 * 并且有助于减轻编写“样板”代码的负担。注解的优点还包括：更加干净易读的代码以及编译期类型检查等。
 * 每当你创建描述符性质的类或接口时，一旦其中包含了重复性的工作，那就可以考虑使用注解来简化与自动化该过程。
 */
public class Testable {
    public void execute(){
        System.out.println("Executing..");
    }

    @Test void testExecute(){
        execute();
    }

/* 常见的方式
   @Test
   void testExecute(){
        execute();
    }*/
}
