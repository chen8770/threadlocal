package feature;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class StaticMethodReference {
    public static void main(String args[]){
        Function<List<Integer>, Integer> maxFn = Collections::max;
        System.out.println(maxFn.apply(Arrays.asList(1, 10, 3, 5)));

        Function<List<Integer>, Integer> maxFn1 = (numbers) -> Collections.min(numbers);
        System.out.println(maxFn1.apply(Arrays.asList(1, 10, 3, 5)));

    }
}
