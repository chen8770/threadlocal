package threadlocal;

public class Test {

    public static Object o = new Object();

    public static void main(String[] args) {
        Test t1 = new Test();
        Test t2 = new Test();
        Test t3 = new Test();
        Test t4 = new Test();
        Test t5 = new Test();

        System.out.println(t1.o);
        System.out.println(t2.o);
        System.out.println(t3.o);
        System.out.println(t4.o);
        System.out.println(t5.o);
    }
}
