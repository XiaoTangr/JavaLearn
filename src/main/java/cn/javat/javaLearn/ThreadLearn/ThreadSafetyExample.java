package cn.javat.javaLearn.ThreadLearn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全示例
 * 演示线程不安全和线程安全的情况
 */
public class ThreadSafetyExample {
    
    // 共享资源
    private static int unsafeCounter = 0;
    private static int synchronizedCounter = 0;
    private static int lockCounter = 0;
    private static final Object mutex = new Object();
    private static final Lock reentrantLock = new ReentrantLock();
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 线程安全示例 ===");
        
        // 1. 线程不安全示例
        unsafeExample();
        
        Thread.sleep(1000);
        System.out.println("\n--------------------\n");
        
        // 2. 使用synchronized关键字保证线程安全
        synchronizedExample();
        
        Thread.sleep(1000);
        System.out.println("\n--------------------\n");
        
        // 3. 使用Lock接口保证线程安全
        lockExample();
    }
    
    /**
     * 线程不安全示例
     */
    private static void unsafeExample() throws InterruptedException {
        System.out.println("--- 线程不安全示例 ---");
        unsafeCounter = 0;
        
        // 创建多个线程同时修改共享变量
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    unsafeCounter++; // 非原子操作，线程不安全
                }
            }, "UnsafeThread-" + i);
        }
        
        // 启动所有线程
        for (Thread thread : threads) {
            thread.start();
        }
        
        // 等待所有线程执行完毕
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("期望结果: " + (10 * 1000));
        System.out.println("实际结果: " + unsafeCounter);
        System.out.println("差值: " + (10 * 1000 - unsafeCounter));
    }
    
    /**
     * 使用synchronized关键字保证线程安全
     */
    private static void synchronizedExample() throws InterruptedException {
        System.out.println("--- synchronized关键字保证线程安全 ---");
        synchronizedCounter = 0;
        
        // 创建多个线程同时修改共享变量
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrementSynchronizedCounter();
                }
            }, "SynchronizedThread-" + i);
        }
        
        // 启动所有线程
        for (Thread thread : threads) {
            thread.start();
        }
        
        // 等待所有线程执行完毕
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("期望结果: " + (10 * 1000));
        System.out.println("实际结果: " + synchronizedCounter);
    }
    
    /**
     * 使用synchronized方法增加计数器
     */
    private static synchronized void incrementSynchronizedCounter() {
        synchronizedCounter++;
    }
    
    /**
     * 使用Lock接口保证线程安全
     */
    private static void lockExample() throws InterruptedException {
        System.out.println("--- Lock接口保证线程安全 ---");
        lockCounter = 0;
        
        // 创建多个线程同时修改共享变量
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrementLockCounter();
                }
            }, "LockThread-" + i);
        }
        
        // 启动所有线程
        for (Thread thread : threads) {
            thread.start();
        }
        
        // 等待所有线程执行完毕
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("期望结果: " + (10 * 1000));
        System.out.println("实际结果: " + lockCounter);
    }
    
    /**
     * 使用ReentrantLock增加计数器
     */
    private static void incrementLockCounter() {
        reentrantLock.lock();
        try {
            lockCounter++;
        } finally {
            reentrantLock.unlock();
        }
    }
}