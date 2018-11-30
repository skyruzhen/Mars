package core.effective.inheritance;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/24
 * @since 1.0.0
 */
public class MainTest {
    public static void main(String[] args) {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
        System.out.println(s.getAddCount());

        InstrumentedForwardingSet<String> sf = new InstrumentedForwardingSet<>(new HashSet());
        sf.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
        System.out.println(sf.getAddCount());
        //AbstractCollection
    }

}