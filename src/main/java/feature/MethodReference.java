package feature;

import java.util.function.Function;

public class MethodReference {
    public static void main(String args[]){
        Function<String, Integer> strToLength = String::length;
        System.out.println(strToLength.apply("aaa"));
    }
}
