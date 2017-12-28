package threadlocal;

import java.util.concurrent.TimeUnit;

public class StopTest
{
    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException
    {
        Thread backgroundThread = new Thread(new Runnable()
        {
            public void run()
            {
                int i = 0;
                while (!stopRequested)
                {
                    i++;
                    System.out.println(i);
                }
            }
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(10);
        stopRequested = true;
    }
}
