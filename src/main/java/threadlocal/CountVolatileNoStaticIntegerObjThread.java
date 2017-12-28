package threadlocal;

/**
 * nostatic线程,传递的局部变量，无可见性，所以加载到内存的是初始值，后续累加也是读取工作内存的值，而非主存的值
 */
public class CountVolatileNoStaticIntegerObjThread implements Runnable {
    private volatile Integer num;
    public void run() {
        for(int i=0;i<10;i++){
            num++;
            System.out.println(Thread.currentThread().getName()+"---num = " + num);
        }
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
