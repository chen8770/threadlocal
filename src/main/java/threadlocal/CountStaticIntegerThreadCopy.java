package threadlocal;

public class CountStaticIntegerThreadCopy implements Runnable {
    private static int num;
    public void run() {
            //因为非线程安全，所以读取的num的值是3或者6
            num=3;
            System.out.println(Thread.currentThread().getName()+"---num的值是--"+num);
            num=5;
            System.out.println(Thread.currentThread().getName()+"---num的值是--"+2*num);
    }
}
