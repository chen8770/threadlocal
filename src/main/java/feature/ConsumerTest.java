package feature;

import java.util.function.Consumer;

public class ConsumerTest {
    public static void main(String args[]){
        Consumer<String> messageConsumer = message -> System.out.println(message);
        messageConsumer.accept("我来了，嘎嘎");
    }
}
