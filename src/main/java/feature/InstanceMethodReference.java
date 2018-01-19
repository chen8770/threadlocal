package feature;

import java.util.function.BiFunction;

public class InstanceMethodReference {
    public static void main(String args[]){
        BiFunction<String, String, String> concatFn = String::concat;
        System.out.println(concatFn.apply("liu","xiaohong"));
    }
}
