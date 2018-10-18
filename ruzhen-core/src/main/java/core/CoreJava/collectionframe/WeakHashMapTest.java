package core.CoreJava.collectionframe;

import java.util.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈弱散列映射〉
 *
 * @author lizhen
 * @create 2018/10/16
 * @since 1.0.0
 */
public class WeakHashMapTest {
    public static void main(String[] args) {
        WeakHashMap weakHashMap = new WeakHashMap();
        weakHashMap.put(1, 1);
        weakHashMap.put(2, 2);
        weakHashMap.put(3, 3);

        //--------------------
        IdentityHashMap identityHashMap = new IdentityHashMap();
        identityHashMap.put(1, "1");
        identityHashMap.put(1, "2");

        IdentityHashMap identityHashMap1 = identityHashMap;
        identityHashMap1.clear();
        System.out.println(identityHashMap.size());

        ArrayList<String> strings = new ArrayList<>();
        List<String> safeStrings = Collections.checkedList(strings, String.class);
//        ArrayList rawList = safeStrings;
//        rawList.add(new Date());
//        System.out.println(System.identityHashCode(identityHashMap.get(1)));
//        System.out.println(System.identityHashCode("1"));
    }
}