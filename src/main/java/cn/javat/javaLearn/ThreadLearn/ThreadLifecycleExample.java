package cn.javat.javaLearn.ThreadLearn;

/**
 * 线程生命周期示例
 * 演示线程的各种状态：NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
 */
public class ThreadLifecycleExample {
    
    private static final Object lock = new Object();
    private static boolean signal = false;
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 线程生命周期示例 ===");
        
        // 1. NEW状态 - 线程被创建但尚未启动
        Thread newThread = new Thread(new LifecycleTask(), "LifecycleThread");
        System.out.println("1. 线程创建后状态: " + newThread.getState()); // NEW
        
        // 2. RUNNABLE状态 - 线程启动后进入可运行状态
        newThread.start();
        System.out.println("2. 线程启动后状态: " + newThread.getState()); // RUNNABLE
        
        // 等待一段时间让线程进入不同状态
        Thread.sleep(100);
        System.out.println("3. 线程运行中状态: " + newThread.getState());
        
        // 3. TIMED_WAITING状态 - 线程调用sleep()方法
        Thread.sleep(100);
        System.out.println("4. 线程sleep时状态: " + newThread.getState()); // TIMED_WAITING
        
        // 4. WAITING状态 - 线程调用wait()方法
        Thread.sleep(600);
        System.out.println("5. 线程wait时状态: " + newThread.getState()); // WAITING
        
        // 5. BLOCKED状态 - 线程试图获取已被占用的锁
        blockedStateExample();
        
        // 6. TERMINATED状态 - 线程执行完毕
        newThread.join();
        System.out.println("6. 线程结束后状态: " + newThread.getState()); // TERMINATED
    }
    
    /**
     * 演示BLOCKED状态
     */
    private static void blockedStateExample() throws InterruptedException {
        System.out.println("\n--- BLOCKED状态示例 ---");
        
        Thread blockingThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("BlockingThread 获取到锁");
                try {
                    Thread.sleep(2000); // 占用锁2秒
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "BlockingThread");
        
        Thread blockedThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("BlockedThread 获取到锁");
            }
        }, "BlockedThread");
        
        // 启动blockingThread先获取锁
        blockingThread.start();
        Thread.sleep(100); // 确保blockingThread先获取到锁
        
        // 启动blockedThread，它会因为无法获取锁而进入BLOCKED状态
        blockedThread.start();
        Thread.sleep(100); // 给blockedThread一点时间进入BLOCKED状态
        
        System.out.println("BlockedThread 状态: " + blockedThread.getState()); // BLOCKED
        
        blockingThread.join();
        blockedThread.join();
    }
    
    /**
     * 生命周期任务类
     */
    static class LifecycleTask implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            
            try {
                // RUNNABLE状态
                for (int i = 0; i < 3; i++) {
                    System.out.println(Thread.currentThread().getName() + " 正在运行 - 第" + (i+1) + "次");
                    Thread.sleep(100); // TIMED_WAITING状态
                }
                
                // WAITING状态
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " 进入等待状态");
                    while (!signal) {
                        lock.wait(); // WAITING状态
                    }
                }
                
                System.out.println(Thread.currentThread().getName() + " 继续执行");
                
                // 再次RUNNABLE状态
                for (int i = 0; i < 2; i++) {
                    System.out.println(Thread.currentThread().getName() + " 正在运行 - 第" + (i+4) + "次");
                    Thread.sleep(50);
                }
                
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 被中断");
                Thread.currentThread().interrupt();
            }
            
            System.out.println(Thread.currentThread().getName() + " 执行结束");
        }
    }
    
    /**
     * 唤醒等待线程的方法
     */
    static {
        // 启动一个线程在适当时候唤醒LifecycleTask线程
        Thread signalThread = new Thread(() -> {
            try {
                Thread.sleep(500);
                synchronized (lock) {
                    signal = true;
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        signalThread.setDaemon(true); // 设置为守护线程
        signalThread.start();
    }
}