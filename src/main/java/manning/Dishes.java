package manning;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;

public class Dishes {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));


        var res1 = menu.stream()
                .collect(groupingBy(x -> x.getCalories() > 500 ? FitLevel.FAT : FitLevel.LOW_FAT));

        var res = menu.stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

        var res2 = menu.stream()
                .collect(
                        groupingBy(Dish::getType,
                                groupingBy(x -> x.getCalories() > 500 ? FitLevel.FAT : FitLevel.LOW_FAT,
                                        mapping(Dish::getName, toList()))));

        var res3 = menu.stream()
                        .collect(groupingBy(Dish::getType, averagingInt(Dish::getCalories)));

        System.out.println(res3);


    }

    public enum FitLevel {
        LOW_FAT, FAT
    }
}
