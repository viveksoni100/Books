package modernjavainaction.chap05;

import modernjavainaction.chap04.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static modernjavainaction.chap04.Dish.menu;

/**
 * @author viveksoni100
 */
public class Driver {

    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs = numbers1.stream()
                .flatMap((Integer i) -> numbers2.stream()
                        .map((Integer j) -> new int[]{i, j})
                )
                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                .collect(toList());
        pairs.forEach(pair -> System.out.printf("(%d, %d)", pair[0], pair[1]));
    }
}
