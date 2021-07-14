package modernjavainaction.chap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilteringApples {

    public static void main(String... args) {

        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        );

        List<Movie> myFavMovies = Arrays.asList(
                new Movie("Inception", "SciFi Thriller"),
                new Movie("Golmaal", "Comedy"),
                new Movie("Hera pheri", "Comedy"),
                new Movie("Enemy at the gates", "History"));

        System.out.println("=================================================");
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        System.out.println(result);

        System.out.println("=================================================");
        Function<Integer, Integer> f2 = x -> x + 1;
        Function<Integer, Integer> g2 = x -> x * 2;
        Function<Integer, Integer> h2 = f.compose(g);
        int result2 = h2.apply(1);
        System.out.println(result2);

        System.out.println("=================================================");
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed()
                            .thenComparing(Apple::getColor));
        System.out.println(inventory);

        System.out.println("=================================================");
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println(inventory);

        System.out.println("=================================================");
        genericFilterDemo(inventory, myFavMovies);

        System.out.println("=================================================");
        parallelStreamDemo(myFavMovies);
        printMovies(myFavMovies);

        System.out.println("=================================================");
        printApples(inventory);

    }

    private static void genericFilterDemo(List<Apple> inventory, List<Movie> myFavMovies) {
        List<Movie> comedyMovies = genericFilter(myFavMovies, (Movie m) -> m.getGenre().equals("Comedy"));
        System.out.println(comedyMovies);
        List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        System.out.println(heavyApples2);
    }

    private static void parallelStreamDemo(List<Movie> myFavMovies) {
        List<Movie> movies = myFavMovies.parallelStream()
                .filter((Movie m) -> m.getGenre().equals("Comedy"))
                .collect(Collectors.toList());
        System.out.println(movies);
    }

    private static void printMovies(List<Movie> myFavMovies) {
        List<Movie> comedyMovies = filterMovies(myFavMovies, (Movie m) -> m.getGenre().equals("Comedy"));
        System.out.println(comedyMovies);
    }

    public static void printApples(List<Apple> inventory) {
        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApples2);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        System.out.println(heavyApples2);

        // []
        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor()));
        System.out.println(weirdApples);
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static <T>List<T> genericFilter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Movie> filterMovies(List<Movie> movie, Predicate<Movie> m) {
        List<Movie> result = new ArrayList<>();
        for (Movie mv : movie) {
            if (m.test(mv)) {
                result.add(mv);
            }
        }
        return result;
    }

    public static class Apple {

        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color='%s', weight=%d}", color, weight);
        }

    }

    public static class Movie {

        private String name;
        private String genre;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public Movie(String name, String genre) {
            super();
            this.name = name;
            this.genre = genre;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return "Movie [name=" + name + ", genre=" + genre + "]";
        }


    }

}
