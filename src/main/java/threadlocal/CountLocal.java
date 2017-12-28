package threadlocal;

public class CountLocal {
    /**
     * 数据线程私有化
     */
    public static final ThreadLocal<Integer> count=new ThreadLocal<Integer>();
    public static Integer get(){
        return count.get();
    }

    public static void set(Integer value){
        count.set(value);
    }
}
