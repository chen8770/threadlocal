package threadlocal;

public class CountStaticInteger {
    /**
     * 实时可见的共享,无序性
     * 如果不是非实时可见的，欢迎大家用事实验证
     */
    public static Integer count=0;
    public static int get(){
        return count;
    }

    public static void set(int value){
        count=value;
    }
}
