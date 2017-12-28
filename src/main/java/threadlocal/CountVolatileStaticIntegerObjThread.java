package threadlocal;

/**
 * static线程，满足可加性，有序性，但是传递的值并没有可见性，会导致初始值疯狂重写的情况,数据的准确性大大降低
 */
public class CountVolatileStaticIntegerObjThread implements Runnable {
    private static volatile Integer num;
    public void run() {
        for(int i=0;i<10;i++){
            num++;
            System.out.println(Thread.currentThread().getName()+"---num = " + num);
        }
    }
    public void setNum(Integer num1) {
        num = num1;
    }
}
