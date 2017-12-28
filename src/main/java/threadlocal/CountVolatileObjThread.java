package threadlocal;

/**
 * static线程,满足volatile的规则，共享、可见、有序
 */
public class CountVolatileObjThread implements Runnable {
    private CountVolatileInteger obj;
    public void run() {
        for(int i=0;i<10;i++){
            obj.set(obj.get()+1);
            System.out.println(Thread.currentThread().getName()+"---num = " + obj.get());
        }
    }

    public void setObj(CountVolatileInteger obj) {
        this.obj = obj;
    }
}
