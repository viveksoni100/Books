package modernjavainaction.chap04;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static modernjavainaction.chap04.Dish.menu;

public class HighCaloriesNames {

  public static void main(String[] args) {

      int portNumber = 8080;
      // Runnable r = () -> System.out.println(portNumber);    // throw error cause local variable that we want to use
                                                            // in lambda should be final, violation of #imperativeProgramming
                                                            // #mutatesAnOuterVariable
      portNumber = 9090;

      List<String> names = menu.stream()
              .filter((Dish d) -> d.getCalories() > 300)
              .map(d -> d.getName())
              .limit(3)
              .collect(toList());
      System.out.println(names);

      System.out.println("---");

      List<Dish> sortedDishes = menu.stream()
              .filter((Dish d) -> d.getCalories() > 300)
              .limit(3)
              .collect(Collectors.toList());
      System.out.println(sortedDishes);
  }

}
