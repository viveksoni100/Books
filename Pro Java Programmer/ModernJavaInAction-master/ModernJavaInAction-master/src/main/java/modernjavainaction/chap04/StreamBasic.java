package modernjavainaction.chap04;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

  public static void main(String... args) {

    List<String> title = Arrays.asList("Modern", "Java", "In", "Action");
    Stream<String> s = title.stream();
    s.forEach(System.out::println);

    // Java 7
    getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

    System.out.println("---");

    // Java 8
    getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
    getDishObjectsInJava8(Dish.menu).forEach(System.out::println);
  }

  public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
    List<Dish> lowCaloricDishes = new ArrayList<>();
    for (Dish d : dishes) {
      if (d.getCalories() < 400) {
        lowCaloricDishes.add(d);
      }
    }
    List<String> lowCaloricDishesName = new ArrayList<>();
    Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
      @Override
      public int compare(Dish d1, Dish d2) {
        return Integer.compare(d1.getCalories(), d2.getCalories());
      }
    });
    for (Dish d : lowCaloricDishes) {
      lowCaloricDishesName.add(d.getName());
    }
    return lowCaloricDishesName;
  }

  public static List<Dish> getDishObjectsInJava8(List<Dish> dishes) {
    return dishes.stream()
            .filter(d -> d.getCalories() < 400)
            .sorted(comparing(Dish::getCalories))
            .collect(toList());
  }

  public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
    return dishes.stream()
        .filter(d -> d.getCalories() < 400)
        .sorted(comparing(Dish::getCalories))
        .map(Dish::getName)
        .collect(toList());
  }

}
