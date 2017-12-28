package threadlocal;

public class CountVolatileInteger {
    /**
     * 有没有static效果相同
     */
    public volatile Integer count=0;
    public int get(){
        return count;
    }

    public void set(int value){
        count=value;
    }
}
