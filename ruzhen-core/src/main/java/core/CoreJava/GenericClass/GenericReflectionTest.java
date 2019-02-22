package core.CoreJava.GenericClass;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 〈一句话功能简述〉<br>
 * 〈泛型反射API示例〉
 *
 * @author lizhen
 * @create 2018/10/11
 * @since 1.0.0
 */
public class GenericReflectionTest {
    public static void main(String[] args) {
        String name;
        if(args.length > 0) name = args[0];
        else{
            try(Scanner in = new Scanner(System.in))
            {
                System.out.println("Enter class name (e.g. java.httpclient.Collection):");
                name = in.next();
            }

            Class<?> cl = null;
            try {
                cl = Class.forName(name);
                printClass(cl);
                for(Method m: cl.getDeclaredMethods())
                    printMethod(m);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }




        }
    }
    private static void printClass(Class<?> cl) {
        System.out.print(cl);
        printTypes(cl.getTypeParameters(), "<",",",">", true);
        Type sc  = cl.getGenericSuperclass();
        if(sc != null){
            System.out.print(" extends ");
            printTypes(sc, false);
        }
        printTypes(cl.getGenericInterfaces(), " implements ", ",","",false);
        System.out.println();
    }


    private static void printMethod(Method m) {
        String name = m.getName();
        System.out.print(Modifier.toString(m.getModifiers()));
        System.out.print(" ");
        printTypes(m.getTypeParameters(), "<",", ", "> ", true);
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(m.getGenericParameterTypes(), "",", ", "", false );
        System.out.println(")");
    }

    private static void printTypes(Type type, boolean isDefinition) {
        if(type instanceof Class){
            Class<?> t = (Class<?>) type;
            System.out.print(t.getName());
        }else if(type instanceof TypeVariable){
            TypeVariable<?> t = (TypeVariable<?>) type;
            System.out.print(t.getName());
            if(isDefinition) printTypes(t.getBounds(), " extends ", " & ", ",",false);
        }else if(type instanceof WildcardType){
            WildcardType t = (WildcardType) type;
            System.out.print("?");
            printTypes(t.getUpperBounds(), " extends ", " & ", "", false);
            printTypes(t.getLowerBounds(), " super ", " & ", "", false);
        }else if(type instanceof ParameterizedType){
            ParameterizedType t = (ParameterizedType) type;
            Type owner = t.getOwnerType();
            if(owner != null){
                printTypes(owner, false);
                System.out.print(".");
            }
            printTypes(t.getRawType(), false);
            printTypes(t.getActualTypeArguments(), "<", ",", ">",false);
        }else if(type instanceof GenericArrayType){
            GenericArrayType t = (GenericArrayType) type;
            System.out.print("");
            printTypes(t.getGenericComponentType(), isDefinition);
            System.out.print("[]");
        }
    }

    private static void printTypes(Type[] types, String pre, String sep, String suf, boolean isDefinition) {
        if(pre.equals(" extends ") && Arrays.equals(types, new Type[]{Object.class})) return;
        if(types.length > 0) System.out.print(pre);
        for(int i = 0; i < types.length; i++){
            if( i > 0) System.out.print(sep);
            printTypes(types[i], isDefinition);
            if(types.length > 0) System.out.print(suf);
        }
    }
}