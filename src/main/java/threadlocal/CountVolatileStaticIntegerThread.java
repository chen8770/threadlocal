package threadlocal;

/**
 * static线程,满足volatile的规则，共享、可见、有序
 */
public class CountVolatileStaticIntegerThread implements Runnable {
    private static volatile int num=0;
    public void run() {
        for(int i=0;i<10;i++){
            num++;
            System.out.println(Thread.currentThread().getName()+"---num = " + num);
        }
    }
}
