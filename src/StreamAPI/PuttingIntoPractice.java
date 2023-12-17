package StreamAPI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

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

        Stream<Transaction> myStream = transactions.stream();


        //1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
        myStream.filter(trader->trader.getYear()==2011)
                .sorted((value1, value2)->value1.getValue()- value2.getValue())
//                .sorted(Comparator.comparingInt(Transaction::getValue)) //another way
                .forEach(System.out::println);

        // 2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        myStream.map(trader ->trader.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        // 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        myStream.map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .map(Trader::getName)
                .sorted()
                .distinct()
                .forEach(System.out::println);

        // 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
        myStream.map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .forEach(System.out::println);

        //5. Выяснить, существует ли хоть один трейдер из Милана.
        System.out.println(myStream.map(Transaction::getTrader)
                .map(Trader::getCity)
                .anyMatch(city -> city.equals("Milan")));


        //6. Вывести суммы всех транзакций трейдеров из Кембриджа.
                myStream.filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")) //взяли всех трейлеров из кембридж
                .mapToInt(Transaction::getValue)
                        .forEach(System.out::println);

        //7. Какова максимальная сумма среди всех транзакций?
        System.out.println(myStream.map(Transaction::getValue)
                .max(Comparator.naturalOrder()));

        //8. Найти транзакцию с минимальной суммой.
        System.out.println(myStream.map(Transaction::getValue)
                .min(Comparator.naturalOrder()));

    }
}
