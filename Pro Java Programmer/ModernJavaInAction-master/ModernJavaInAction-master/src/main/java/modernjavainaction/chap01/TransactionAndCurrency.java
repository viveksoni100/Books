package modernjavainaction.chap01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author viveksoni100
 */
public class TransactionAndCurrency {

    public static void main(String[] args) {

        List<Transaction> transactions = Arrays.asList(
                new Transaction(100, new Currency("USD")),
                new Transaction(200, new Currency("USD")),
                new Transaction(300, new Currency("USD")),
                new Transaction(400, new Currency("INR")),
                new Transaction(500, new Currency("INR")),
                new Transaction(600, new Currency("INR"))
        );

        System.out.println("*********Filter by transactions*********");
        List<Transaction> transactionsFiltered = transactions.stream()
                .filter((Transaction t) -> t.getPrice() > 400)
                .collect(Collectors.toList());
        System.out.println(transactionsFiltered);

        System.out.println("*********Filter by currencies*********");
        List<Currency> currencies = transactions.stream()
                .filter((Transaction t) -> t.getCurrency().getCurrencyName().equals("USD"))
                .map(tr -> tr.getCurrency()).collect(Collectors.toList());
        System.out.println(currencies);

        System.out.println("*********Filter by currencies*********");
        transactions.stream()
                .filter((Transaction t) -> t.getCurrency().getCurrencyName().equals("USD"))
                .map(tr -> tr.getCurrency())
                .forEach(System.out::println);

        System.out.println("*********Returns only prices that we mapped*********");
        transactions.stream()
                .filter((Transaction t) -> t.getPrice() > 200)
                .map(tr -> tr.getPrice())
                .forEach(System.out::println);

        System.out.println("*********Returns objects of transactions*********");
        transactions.stream()
                .filter((Transaction t) -> t.getPrice() > 200)
                .forEach(System.out::println);

    }

    public static class Transaction {

        private int price;
        private Currency currency;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public Currency getCurrency() {
            return currency;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "price=" + price +
                    ", currency=" + currency +
                    '}';
        }

        public Transaction(int price, Currency currency) {
            this.price = price;
            this.currency = currency;
        }
    }

    public static class Currency {
        private String currencyName;

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        @Override
        public String toString() {
            return "Currency{" +
                    "currencyName='" + currencyName + '\'' +
                    '}';
        }

        public Currency(String currencyName) {
            this.currencyName = currencyName;
        }
    }

}
