package threadlocal;

import java.util.concurrent.TimeUnit;

public class CountStaticIntegerThread implements Runnable {
    public void run() {
        for(int i=0;i<100;i++){
//            if(i==30){
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            int count=CountStaticInteger.get();
            CountStaticInteger.set(count+1);
            System.out.println(Thread.currentThread().getName()+"--"+count+"---"+CountStaticInteger.get());
        }
    }
}
