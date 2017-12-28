package threadlocal;

public class CountLocalThread implements Runnable {
    public void run() {
        for(int i=1;i<=100;i++){
            if(CountLocal.get()!=null){
                CountLocal.set(CountLocal.get()+1);
            }else{
                CountLocal.set(i);
            }

            System.out.println(Thread.currentThread().getName()+"---"+CountLocal.get());
        }
        System.out.println(Thread.currentThread() + "end");
    }
}
