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