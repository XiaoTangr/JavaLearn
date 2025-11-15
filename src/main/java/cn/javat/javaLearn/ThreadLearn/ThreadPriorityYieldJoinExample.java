package cn.javat.javaLearn.ThreadLearn;

/**
 * 线程优先级、休眠、让步与插队示例
 */
public class ThreadPriorityYieldJoinExample {
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 线程优先级、休眠、让步与插队示例 ===");
        System.out.println("主线程优先级: " + Thread.currentThread().getPriority());
        
        // 1. 线程优先级示例
        priorityExample();
        
        Thread.sleep(1000);
        System.out.println("\n--------------------\n");
        
        // 2. 线程休眠示例
        sleepExample();
        
        Thread.sleep(1000);
        System.out.println("\n--------------------\n");
        
        // 3. 线程让步示例
        yieldExample();
        
        Thread.sleep(1000);
        System.out.println("\n--------------------\n");
        
        // 4. 线程插队示例
        joinExample();
    }
    
    /**
     * 线程优先级示例
     */
    private static void priorityExample() {
        System.out.println("--- 线程优先级示例 ---");
        
        Thread minPriorityThread = new Thread(new CounterTask(10000000), "MinPriorityThread");
        Thread normPriorityThread = new Thread(new CounterTask(10000000), "NormPriorityThread");
        Thread maxPriorityThread = new Thread(new CounterTask(10000000), "MaxPriorityThread");
        
        // 设置不同优先级
        minPriorityThread.setPriority(Thread.MIN_PRIORITY);   // 1
        normPriorityThread.setPriority(Thread.NORM_PRIORITY); // 5 (默认)
        maxPriorityThread.setPriority(Thread.MAX_PRIORITY);   // 10
        
        System.out.println("最小优先级线程: " + minPriorityThread.getPriority());
        System.out.println("普通优先级线程: " + normPriorityThread.getPriority());
        System.out.println("最大优先级线程: " + maxPriorityThread.getPriority());
        
        minPriorityThread.start();
        normPriorityThread.start();
        maxPriorityThread.start();
        
        try {
            minPriorityThread.join();
            normPriorityThread.join();
            maxPriorityThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * 线程休眠示例
     */
    private static void sleepExample() throws InterruptedException {
        System.out.println("--- 线程休眠示例 ---");
        
        Thread sleepThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " 计数: " + i);
                    // 线程休眠1秒
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 被中断");
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " 执行结束");
        }, "SleepThread");
        
        long startTime = System.currentTimeMillis();
        sleepThread.start();
        sleepThread.join(); // 等待线程执行完毕
        long endTime = System.currentTimeMillis();
        
        System.out.println("线程实际执行时间: " + (endTime - startTime) + " 毫秒");
    }
    
    /**
     * 线程让步示例
     */
    private static void yieldExample() throws InterruptedException {
        System.out.println("--- 线程让步示例 ---");
        
        YieldThread yieldThread1 = new YieldThread("YieldThread-1");
        YieldThread yieldThread2 = new YieldThread("YieldThread-2");
        
        yieldThread1.start();
        yieldThread2.start();
        
        yieldThread1.join();
        yieldThread2.join();
    }
    
    /**
     * 线程插队示例
     */
    private static void joinExample() throws InterruptedException {
        System.out.println("--- 线程插队示例 ---");
        
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            try {
                Thread.sleep(2000); // 模拟耗时操作
                System.out.println(Thread.currentThread().getName() + " 执行完毕");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 被中断");
                Thread.currentThread().interrupt();
            }
        }, "Thread-1");
        
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            try {
                Thread.sleep(3000); // 模拟耗时操作
                System.out.println(Thread.currentThread().getName() + " 执行完毕");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 被中断");
                Thread.currentThread().interrupt();
            }
        }, "Thread-2");
        
        thread1.start();
        thread2.start();
        
        // 主线程等待thread1执行完毕后再继续
        System.out.println("主线程等待 " + thread1.getName() + " 执行完毕...");
        thread1.join();
        System.out.println(thread1.getName() + " 已执行完毕，主线程继续执行");
        
        // 继续等待thread2执行完毕
        thread2.join();
        System.out.println(thread2.getName() + " 已执行完毕");
    }
    
    /**
     * 计数任务类
     */
    static class CounterTask implements Runnable {
        private final int countTo;
        
        public CounterTask(int countTo) {
            this.countTo = countTo;
        }
        
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            long counter = 0;
            for (int i = 0; i < countTo; i++) {
                counter++;
            }
            long endTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + 
                              " 计数到 " + counter + 
                              "，耗时 " + (endTime - startTime) + " 毫秒");
        }
    }
    
    /**
     * 让步线程类
     */
    static class YieldThread extends Thread {
        public YieldThread(String name) {
            super(name);
        }
        
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(getName() + " 计数: " + i);
                // 让出CPU执行权给其他同优先级线程
                Thread.yield();
            }
            System.out.println(getName() + " 执行结束");
        }
    }
}