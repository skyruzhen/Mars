package core.effectiveJava.generification;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/25
 * @since 1.0.0
 */
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public<T> void putFavorite(Class<T> type, T instance){
        if(type == null) throw new NullPointerException("Type is null");
        favorites.put(type, instance);
    }
    public <T> T getFavorite(Class<T> type){
        return type.cast(favorites.get(type));
    }
}