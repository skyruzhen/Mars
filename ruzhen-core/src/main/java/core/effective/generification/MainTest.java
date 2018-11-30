package core.effective.generification;

import java.util.*;

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
      /*  Stack<Number> stack = new Stack();
        Iterable<Integer> integers = new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return null;
            }
        };
        stack.pushAll(integers);

        Collection<Object> objects = new ArrayList<>();

        stack.popAll(objects);
*/

        //Collections
       // List
        Set<String> guys = new HashSet<>(Arrays.asList("Tom", "Dick", "Harry"));
        Set<String> stooges = new HashSet<>(Arrays.asList("Larry", "Moe", "Curly"));
        Set<String> aflCio = union(guys, stooges);
        System.out.println(aflCio);

        Set<Integer> integers = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Double> doubles = new HashSet<>(Arrays.asList(4D, 5D, 6D));
        Set<Number> numbers = union(integers, doubles);
        System.out.println(numbers);

        Favorites f = new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        System.out.println(favoriteString+" "+favoriteInteger);

    }

    public static<E> Set union(Set<? extends E> s1, Set<? extends E> s2){
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }

    public static<T> T max(List<? extends T> list){
        Iterator<? extends T> i = list.iterator();
        T result = i.next();

        return result;
    }

    public static <E> void swap0(List<E> list, int i, int j){
        list.set(i, list.set(j, list.get(i)));
    }

    public static void swap1(List<?> list, int i, int j){
       swapHelper(list, i, j);
    }

    private static<E> void swapHelper(List<E> list, int i, int j){
        list.set(i, list.set(j, list.get(i)));
    }




}