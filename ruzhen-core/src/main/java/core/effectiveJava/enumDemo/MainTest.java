package core.effectiveJava.enumDemo;

import java.util.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/25
 * @since 1.0.0
 */
public class MainTest {
    public static void main(String[] args) {
        Planet mars = Planet.MARS;
        System.out.println(mars.getMass());
        System.out.println(Arrays.asList(Planet.values()));

        Herb[] garden = {new Herb("abc",Herb.Type.ANNUAL)};
       /* Set<Herb>[] herbsByType = new Set[Herb.Type.values().length];
        for(int i = 0; i < herbsByType.length; i++){
            herbsByType[i] = new HashSet<Herb>();
        }
        for(Herb h :garden){
            herbsByType[h.type.ordinal()].add(h);
        }*/

        Map<Herb.Type, Set<Herb>> herbsByType = new EnumMap<Herb.Type, Set<Herb>>(Herb.Type.class);
        for(Herb.Type t:Herb.Type.values()){
            herbsByType.put(t, new HashSet<>());
        }
        for(Herb h:garden){
            herbsByType.get(h.type).add(h);
        }
        System.out.println(herbsByType);



    }
}