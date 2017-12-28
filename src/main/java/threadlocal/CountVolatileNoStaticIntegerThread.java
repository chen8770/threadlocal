package threadlocal;

/**
 * 非static线程,和普通的变量没有区别，可以去掉volatile，因为他是私有的，无可见性一说。唯一可能影响的就是有序
 */
public class CountVolatileNoStaticIntegerThread implements Runnable {
    private volatile  int num=0;
    public void run() {
        for(;;){
            num++;
            System.out.println(Thread.currentThread().getName()+"---num = " + num);
            if(num>10){
                break;
            }
        }
    }
}
