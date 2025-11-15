package cn.javat.javaLearn.ThreadLearn;

/**
 * 线程基础示例
 * 演示线程的创建、启动、生命周期等基本概念
 */
public class ThreadBasicsExample {
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 线程基础示例 ===");
        
        // 1. 创建线程的方式一：继承Thread类
        MyThread myThread = new MyThread("MyThread-1");
        System.out.println(myThread.getName() + " 线程状态: " + myThread.getState());
        
        // 2. 创建线程的方式二：实现Runnable接口
        Thread runnableThread = new Thread(new MyRunnable(), "RunnableThread-1");
        
        // 3. 启动线程
        myThread.start();
        System.out.println(myThread.getName() + " 启动后状态: " + myThread.getState());
        
        runnableThread.start();
        System.out.println(runnableThread.getName() + " 启动后状态: " + runnableThread.getState());
        
        // 4. 等待线程执行完毕
        myThread.join();
        runnableThread.join();
        
        System.out.println(myThread.getName() + " 执行完毕后状态: " + myThread.getState());
        System.out.println(runnableThread.getName() + " 执行完毕后状态: " + runnableThread.getState());
    }
    
    /**
     * 通过继承Thread类创建线程
     */
    static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }
        
        @Override
        public void run() {
            System.out.println(getName() + " 开始执行，当前状态: " + getState());
            try {
                Thread.sleep(1000); // 模拟工作
                System.out.println(getName() + " 执行中...");
            } catch (InterruptedException e) {
                System.out.println(getName() + " 被中断");
                Thread.currentThread().interrupt();
            }
            System.out.println(getName() + " 执行结束，当前状态: " + getState());
        }
    }
    
    /**
     * 通过实现Runnable接口创建线程
     */
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + " 开始执行，当前状态: " + currentThread.getState());
            try {
                Thread.sleep(1500); // 模拟工作
                System.out.println(currentThread.getName() + " 执行中...");
            } catch (InterruptedException e) {
                System.out.println(currentThread.getName() + " 被中断");
                Thread.currentThread().interrupt();
            }
            System.out.println(currentThread.getName() + " 执行结束，当前状态: " + currentThread.getState());
        }
    }
}