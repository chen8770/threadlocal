package feature;

import java.util.function.Function;

public class FunctionTest {
    public static void main(String args[]){
        Function<String, String> toUpperCase = name -> name.toUpperCase();
        System.out.println(toUpperCase.apply("aaa"));
    }
}
