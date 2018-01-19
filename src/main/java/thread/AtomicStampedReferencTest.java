package thread;

import threadlocal.CountLocalThread;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferencTest {
    private static Double version=1.0D;
    private static Integer val=1;
    public static void main(String a[]){
        AtomicStampedReference<Double> asr=new AtomicStampedReference<Double>(version,val);
        for(int i=0;i<2;i++){
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    Double newVersion=Math.random();
                    Integer newVal=2;
                    //版本号变化导致无法成功
                    if(!asr.weakCompareAndSet(newVersion,newVersion,asr.getStamp(),newVal)){
                        version=newVersion;
                        System.out.println(Thread.currentThread().getName()+"--false-"+asr.getReference()+"---"+asr.getStamp());
                    }else{
                        System.out.println(Thread.currentThread().getName()+"--true-"+asr.getReference()+"---"+asr.getStamp());

                    }
                }
            });
            t.start();

        }
    }
}
