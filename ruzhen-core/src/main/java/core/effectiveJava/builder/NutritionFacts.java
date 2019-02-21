package core.effectiveJava.builder;

/**
 * 〈一句话功能简述〉<br>
 * 〈Builder Pattern〉
 *
 * @author lizhen
 * @create 2018/6/15
 * @since 1.0.0
 */
public class NutritionFacts {
    private  final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder{
        private final int servingSize;
        private final int servings;

        //initialized to default vlaue
        private int calories    = 0;
        private int fat         = 0;
        private int cabohydrate = 0;
        private int sodium      = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }
        public Builder calories(int val){
            calories = val;
            return this;
        }
        public Builder fat(int val){
            fat = val;
            return this;
        }
        public Builder carbohydrate(int val){
            cabohydrate = val;
            return this;
        }
        public Builder sodium(int val){
            sodium = val;
            return this;
        }

        public NutritionFacts builder(){
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder){
        //第2条： 遇到多个构造器参数时要考虑用构造器
        servingSize  = builder.servingSize;
        servings     = builder.servings;
        calories     = builder.calories;
        fat          = builder.fat;
        sodium       = builder.sodium;
        carbohydrate = builder.cabohydrate;
    }
}