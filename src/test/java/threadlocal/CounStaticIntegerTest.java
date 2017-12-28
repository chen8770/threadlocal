package threadlocal;

import java.util.concurrent.*;

public class CounStaticIntegerTest {
    public static void main(String a[]){
        /**
         * 为什么没有乱序执行呢？？static什么时候更新主存，感觉每次都是主存获取的
         */
        for(int i=0;i<50;i++){
            Thread t=new Thread(new CountStaticIntegerThreadCopy());
            t.start();
        }
    }

    public static void main2(String a[]){
        /**
         * 为什么没有乱序执行呢？？static什么时候更新主存，感觉每次都是主存获取的
         */
        BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>(300);
        ExecutorService exe=new ThreadPoolExecutor(10,20,5000L, TimeUnit.SECONDS,WORK_QUEUE,new ThreadPoolExecutor.CallerRunsPolicy());
        for(int i=0;i<10;i++){
            exe.submit(new CountStaticIntegerThread());
        }
    }
}
