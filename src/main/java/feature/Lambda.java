package feature;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Lambda {
    public static void main(String a[]){
//        List<String> names = Arrays.asList("shekhar", "rahul", "sameer");
//        Collections.sort(names, (first, second) -> first.length() - second.length());
//        System.out.println(names);

        //In Java 8:
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API",
                "Date and Time API");
        features.forEach(n -> System.out.println(n));

        // Even better use Method reference feature of Java 8
        // method reference is denoted by :: (double colon) operator
        // looks similar to score resolution operator of C++
        features.forEach(System.out::println);

        Function<List<Integer>, Integer> maxFn = Collections::max;
        maxFn.apply(Arrays.asList(1, 10, 3, 5));
    }
}
