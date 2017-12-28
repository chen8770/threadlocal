package threadlocal;

public class CounVolatileIntegerTest {
    public static void main1(String a[]){
        for(int i=0;i<10;i++){
            Thread t=new Thread(new CountVolatileStaticIntegerThread());
            t.start();
        }
    }

    public static void main2(String a[]){
        for(int i=0;i<10;i++){
            Thread t=new Thread(new CountVolatileNoStaticIntegerThread());
            t.start();
        }
    }

    public static void main3(String a[]){
        Integer num=new Integer(0);
        for(int i=0;i<10;i++){
            CountVolatileStaticIntegerObjThread obj=new CountVolatileStaticIntegerObjThread();
            obj.setNum(num);
            Thread t=new Thread(obj);
            t.start();
        }
    }
    public static void main4(String a[]){
        Integer num=new Integer(0);
        for(int i=0;i<10;i++){
            CountVolatileNoStaticIntegerObjThread obj=new CountVolatileNoStaticIntegerObjThread();
            obj.setNum(num);
            Thread t=new Thread(obj);
            t.start();
        }
    }

    public static void main(String a[]){
        CountVolatileInteger num=new CountVolatileInteger();
        for(int i=0;i<10;i++){
            CountVolatileObjThread obj=new CountVolatileObjThread();
            obj.setObj(num);
            Thread t=new Thread(obj);
            t.start();
        }
    }
}
