static:
    1：共享值，满足可见性
    2：方便访问变量时
    3：非线程安全
    4: 静态区开辟单独共享，无副本的内存
volatile：
    1：可见性
    2：有序性
    3：非线程安全
    4：方法区开辟对象的内存
1：组合使用就是特性组合,常规使用方法
2：单独使用：如果变量标记volatile，作为私有变量无意义,如果赋值是工作内存读取，非主存每次读取
3：单独使用：如果变量标记volatile，作为共享变量满足volatile特性
4：static,volatile需要有初始化，
5：static,volatile可见性没区别，

ThreadLocal
    1:创建ThreadLocalMap，赋值给Thread.threadLocals
    2:ThreadLocal的对象做key，具体值放入map
    3：ThreadLocalMap底层实现是个数组，通过key的hash设置数组对应位置的值
    4：获取值得时候，ThreadLocal的对象做key,hash获取数据的对应位置的值
    5：主要是提供了线程保持对象的方法和避免参数传递的方便的对象访问方式
 ThreadLocal底层维护是Entry[]数组
 ThreadLocal底层维护一个static AtomicInteger nextHashCode
 ThreadLocal每new一个对象，累加器加上0x61c88647，根据规则生成数组的位置
 读取数据的时候，生成数组定位的逻辑相同，最后要验证一下数取出来的Entry的key 是否和要获取的key（非数组的定位）相同

 关于：WeakReference后续深入研究一下
ThreadLocal的总结思考

ThreadLocal应该是java程序员面试常被问到的一个语言特性问题。问的最多的可能就是，ThreadLocal的底层数据结构是什么？
下面的回答基本上可以忽悠面试官了：
        1、ThreadLocal底层的数据模型是ThreadLocalMap，该Map是一个基于开放地址法实现的哈希表，key是当前的threadlocal对象，value是当前线程放在这个threadlocal里面的值；
        2、每个线程持有一个ThreadLocalMap对象A，A里面放的是和当前线程相关的threadlocal；
        3、如果认真看过源码，还会知道ThreadLocalMap的实现和WeakHashMap实现有异曲同工之妙，都通过WeakReference封装了key值，防止可怕的内存泄漏；
        4、再举个get()数据的例子。第一步得到当前线程；第二步获取当前线程对应的ThreadLocalMap；第三步以当前threadlocal对象为key，去ThreadLocalMap中把值捞出来；

ThreadLocal的内存模型看起来是下面这个图的样子


这个图给人的感觉很别扭，自然也就会产生下面的疑惑：
1、为什么不用个全局的map来实现threadlocal，key为当前threalocal+thread，value为其对应的值。毕竟这个实现简单又易于理解；
2、为什么ThreadLocalMap要用WeakReference来封装key，然后整个代码实现多了一大堆乱七八糟的事情；
3、为什么要用开放地址法实现哈希表，而不用类似于HashMap的链地址法；
      我们从一个示例来分析上面的问题
假设用一个全局map来实现threadlocal，大概的代码可能是下面这样的：

这个山寨的threadlocal也可以像jdk提供的threadlocal一样使用：

功能上看起来是完全满足要求了，但是也带来下面的问题：
       放在这个全局map的变量由谁来清理，什么时候清理？
我能想到两个办法：
1、每个ShanzhaiThreadLocal对象在无用的时候把自己从map中移除掉。可是这样做，是要java程序员回到C的时代么，手动管理内存？而且如果程序抛异常了怎么办，catch所有东西，在finally中remove map么？这样代码会不会太丑陋了？
2、在线程死掉的时候从map中移除相关key-value，毕竟我们的key是用thread+threadlocal计算出来的，这个办法可以一把将所有线程相关的缓存内容都干掉！确实很方便，可是万一线程意外终止呢，在什么时候移除？或者线程不断被复用，就像线程池那样，之前也许已经创建了很多ShanzhaiThreadLocal，它们即使已经无用了，但是依然无法被清理，内存又泄漏了！看起来第二个办法还没第一个靠谱……

看来两个办法都不靠谱，谁有更好的办法请告诉我～～
下面看看jdk的ThreadLocal是怎么解决这个问题的：
1、Thread类中，有个变量"ThreadLocal.ThreadLocalMap threadLocals = null"，ThreadLocalMap就是存放该thread实际数据的地方，这样做解决了上面说的一个问题，线程死掉后，该thread相关的所有threadlocal数据都被清空了，不管线程是不是意外终止，而且是默默的清理掉，不用应用代码操心（毕竟ThreadLocalMap存储的是和当前thread相关的数据，所以做为它的一个独立变量很合理对不对～～）
       2、假设当前thread一直活着（比如赖在线程池中），有些无用的threadlocal对象怎么清理呢？这个就要看接下来WeakReference的用法了～
 下面是ThreadLocalMap的部分源码：

1、这个map的底层数据结构是一个数组，数组是个key-value对象Entry；
       2、Entry对象的key用WeakReference包装了一下；
WeakReference有以下特性：当一个对象A只有WeakReference引用指向它是，那么A在下一次gc的时候就会被回收掉。想象下，如果ThreadLocalMap中某个key已经不用了，最终只会有一个WeakReference指向它，这个key自然就可以被回收掉，不会一直停留在ThreadLocalMap中。

       key回收掉了，value值还在啊，这个怎么回收！！！

       ThreadLocal的get和set方法每次调用时，如果发现当前的entry的key为null（也就是被回收掉了），最终会调用expungeStaleEntry(int staleSlot)方法，该方法会把哈希表当前位置的无用数据清理掉（当然还有别的操作）。不用担心这个动作在遥远的未来才发生，因为只要get，set数据，那么一点点大的哈希表，总是不难产生哈希冲突的，这时候无用的数据就很容易被发现了！

       从上面的源码分析可以看出，假设某个threadlocal对象无效，这个对象本身会在下次gc被回收，对应的value值也会在某次ThreadLocal调用中被释放；如果某个thread死掉了，它对应的threadlocal内容自动释放。

       那为什么要用开放地址法实现hash冲突呢？
      下面说下我个人的理解：
1、链地址法的本质是在哈希表的同一个位置上存储多个元素，那么我们便将要存储到这一位置的多个元素串成链表，并存储链表头。这个方法简单明了，但是效率方面可能要打折扣，如果碰巧诸君不走运，一组元素全哈希到了一个位置，全被放到一个链表中，查找的效率必然可怜（O(n)）；而且链地址法还需要存储指针，必然浪费一部分内存；
       2、开放地址法：所有的元素都被存储在哈希表中，没有链表，没有存储在哈希表之外的元素；更少的内存，更高的内存利用率——开放地址意味着填满哈希表，另外完全逃离了指针，意味着节省了一笔空间。
但是使用开放地址法，在hash冲突很严重的情况下，效率依然很差！

       ThreadLocal中使用0x61c88647这个魔法数据来解决hash冲突的问题，所有的threadlocal对象共享同一个计数器，该计数器每次递增0x61c88647，该数字是一个斐波那契散列乘数，它的优点是通过它散列出来的结果分布会比较均匀，可以很大程度上避免hash冲突。而且ThreadLocalMap需要经常清理无用的对象，使用纯数组形式更方便。
       0x61c88647的测试可以参考http://jerrypeng.me/2013/06/thread-local-and-magical-0x61c88647/

      所以为什么使用开放地址法，个人总结有以下的考虑因素：
      1) 节省内存空间，链表使用的空间大于数组；
      2) ThreadLocal设计的哈希key可以尽可能避免哈希冲突；
      3) 清理数据效率高，毕竟遍历数组比遍历链表效率高；

       使用WeakReference封装key，这样不在使用的ThreadLocal对象可以很快被检查出来并清理掉
       使用全局hashmap保存threadlocal对象，需要应用程序手动清理数据才能确保不会有内存泄露，在发生异常等特殊情况下无法保证这点
