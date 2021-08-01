package modernjavainaction.chap05;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PuttingIntoPractice {

  public static void main(String... args) {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
        new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
    );

    // Query 1: Find all transactions from (filter) year 2011 and sort (sorted) them by value (small to high).
    List<Transaction> tr2011 = transactions.stream()
        .filter(transaction -> transaction.getYear() == 2011)
        .sorted(comparing(Transaction::getValue))
        .collect(toList());
    System.out.println(tr2011);

    // Query 1.1: Find all transactions from year 2011 and sort them by value (high to small) (reversed).
    List<Integer> tr2_2011 = transactions.stream()
            .map(tv -> tv.getValue())
            .filter(tv -> tv > 500)
            .collect(toList());
    System.out.println(tr2_2011);

    // Query 2: What are all the unique (distict) cities where (map) the traders work?
    List<String> cities = transactions.stream()
        .map(transaction -> transaction.getTrader().getCity())
        .distinct()
        .collect(toList());
    cities.forEach(System.out::println);

    // Query 2.1: What are all the unique (distict) cities where (map) the traders work and it starts with (filter) 'M'?
    List<String> cities2 = transactions.stream()
            .map(transaction -> transaction.getTrader().getCity())
            .distinct()
            .filter(transaction -> transaction.startsWith("M"))
            .collect(toList());
    cities2.forEach(System.out::println);

    // Query 3: Find all traders (List<Traders>) from Cambridge (filter) and sort (sorted) them by name.
    List<Trader> traders = transactions.stream()
        .map(Transaction::getTrader)
        .filter(trader -> trader.getCity().equals("Cambridge"))
        .distinct()
        .sorted(comparing(Trader::getName))
        .collect(toList());
    System.out.println(traders);

    System.out.println(":: 3.1 ::");
    // Query 3.1: Find all traders names (List<String>) from Cambridge (filter) and sort (sorted) them by name.
    List<String> tradersNames = transactions.stream()
            .map(Transaction::getTrader)
            .filter(trader -> trader.getCity().equals("Cambridge"))
            .distinct()
            .sorted(comparing(Trader::getName).reversed())
            .map(Trader::getName)
            .filter(n -> n.startsWith("A"))
            .collect(toList());
    tradersNames.forEach(System.out::println);

    System.out.println(":: 4 ::");
    // Query 4: Return a string of all traders' names sorted alphabetically.
    transactions.stream()
        .map(transaction -> transaction.getTrader().getName())
        .distinct()
        .sorted().forEach(System.out::println);
    System.out.println(":: :: ::");

    // Query 5: Are there any trader based in Milan?
    boolean milanBased = transactions.stream()
        .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
    System.out.println(milanBased);

    System.out.println(":: 5.1 ::");
    // Query 5.1: Are there any transaction in 2013?
    boolean tr2013 = transactions.stream()
            .anyMatch(transaction -> transaction.getYear() == 2013);
    System.out.println(tr2013);

    // Query 6: Print all transactions' values from the traders living in Cambridge.
    transactions.stream()
        .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
        .map(Transaction::getValue)
        .forEach(System.out::println);

    System.out.println(":: 7 ::");
    // Query 7: What's the highest value in all the transactions?
    int highestValue = transactions.stream()
        .map(Transaction::getValue)
        .reduce(0, Integer::sum); //max //min
    System.out.println(highestValue);

    // Find the transaction with the smallest value
    Optional<Transaction> smallestTransaction = transactions.stream()
        .min(comparing(Transaction::getValue));
    // Here I cheat a bit by converting the found Transaction (if any) to a String
    // so that I can use a default String if no transactions are found (i.e. the Stream is empty).
    System.out.println(smallestTransaction.map(String::valueOf).orElse("No transactions found"));
  }

}
