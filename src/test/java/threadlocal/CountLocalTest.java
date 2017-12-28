package threadlocal;

import java.util.concurrent.*;
import org.junit.Test;
public class CountLocalTest {
    /**
     * 新建线程threadlocal不存在数据共享的问题。各自维护自己的数据
     */
    @Test
    public void newMoreThread(){
        for(int i=0;i<10;i++){
            Thread t=new Thread(new CountLocalThread());
            t.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-=-=-=-=-=");
    }

    /**
     * 对于线程池，使用threadlocal，因为存活的线程不会杀死，所以线程的threadLocals会保留数据。
     * 那就达不到数据私有化的效果了。所以使用结束后要确认是否需要清理
     */
    @Test
    public void poolMoreThread(){
        BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>(300);
        ExecutorService exe=new ThreadPoolExecutor(10,20,5000L, TimeUnit.SECONDS,WORK_QUEUE,new ThreadPoolExecutor.CallerRunsPolicy());
        for(int i=0;i<10;i++){
            exe.submit(new CountLocalThread());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-=-=-=-=-=");
    }
}
