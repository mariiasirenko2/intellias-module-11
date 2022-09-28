import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = List.of("Mariia", "Olena", "Petr", "Mark", "Anna", "Zahar", "Dmytro");
        System.out.println("List of names: " + names);

        //Task1
        String formattedOdd = names.stream()
                .filter(s -> names.indexOf(s) % 2 == 0)
                .map(s -> names.indexOf(s) + 1 + "." + s)
                .collect(Collectors.joining(", "));

        System.out.println("Task 1: Numbered odd elements: " + formattedOdd);

        //Task2
        List<String> upperCaseSortedDReverse = names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .toList();

        System.out.println("Task 2: UpperCased reverse ordered elements: " + upperCaseSortedDReverse);

        //Task3
        String[] startArray = new String[]{"1, 2, 0", "4, 5"};

        List<Integer> numbers = Arrays.stream(startArray)
                .flatMap(s -> Stream.of(s.split(", ")))
                .map(Integer::parseInt)
                .sorted().toList();

        System.out.println("Task 3: Numbers from array: " + numbers);


        //Task4
        long a = 25214903917L;
        long c = 11;
        long m = (long) Math.pow(2, 48);
        long seed = 2;

        Stream<Long> infiniteStream = generateInfiniteStream(seed, a, c, m);
        System.out.println("Task 4: Stream of 10 elements : " + infiniteStream.limit(10).toList());


        //Task5
        List<Integer> list1 = List.of(1, 3, 5, 7, 9);
        List<Integer> list2 = List.of(0, 2, 4);

        System.out.println("Task 5: ");
        System.out.println("List a: " + list1);
        System.out.println("List b: " + list2);


        System.out.println("Merged lists: " + zip(list1.stream(), list2.stream()).toList());
    }
    
    public static Stream<Long> generateInfiniteStream(long seed, long a, long c, long m) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> secondStream = new ArrayList<>(first.toList());
        AtomicInteger iterator = new AtomicInteger();

        List<T> mergedStreams = second.collect(ArrayList::new, (l1, t) -> {
                    l1.add(t);
                    l1.add(iterator.get() * 2, secondStream.get(iterator.getAndIncrement()));
                },
                ArrayList::addAll);

        return mergedStreams.stream();
    }
}
