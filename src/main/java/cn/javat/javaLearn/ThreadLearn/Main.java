package cn.javat.javaLearn.ThreadLearn;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Java线程学习示例程序");
        System.out.println("==================");
        System.out.println("请选择要运行的示例:");
        System.out.println("1. 线程基础示例 (创建、启动、基本生命周期)");
        System.out.println("2. 线程优先级、休眠、让步与插队示例");
        System.out.println("3. 线程安全示例 (线程不安全与安全对比)");
        System.out.println("4. 线程生命周期详细示例");
        System.out.println("5. 运行所有示例");
        System.out.print("请输入选项 (1-5): ");
        
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        System.out.println();
        
        try {
            switch (choice) {
                case 1:
                    System.out.println("运行线程基础示例...");
                    ThreadBasicsExample.main(args);
                    break;
                case 2:
                    System.out.println("运行线程优先级、休眠、让步与插队示例...");
                    ThreadPriorityYieldJoinExample.main(args);
                    break;
                case 3:
                    System.out.println("运行线程安全示例...");
                    ThreadSafetyExample.main(args);
                    break;
                case 4:
                    System.out.println("运行线程生命周期详细示例...");
                    ThreadLifecycleExample.main(args);
                    break;
                case 5:
                    System.out.println("运行所有示例...");
                    System.out.println("\n==================== 线程基础示例 ====================");
                    ThreadBasicsExample.main(args);
                    
                    System.out.println("\n==================== 线程优先级等示例 ====================");
                    ThreadPriorityYieldJoinExample.main(args);
                    
                    System.out.println("\n==================== 线程安全示例 ====================");
                    ThreadSafetyExample.main(args);
                    
                    System.out.println("\n==================== 线程生命周期示例 ====================");
                    ThreadLifecycleExample.main(args);
                    break;
                default:
                    System.out.println("无效选项，默认运行线程基础示例...");
                    ThreadBasicsExample.main(args);
                    break;
            }
        } catch (InterruptedException e) {
            System.out.println("程序执行被中断");
            Thread.currentThread().interrupt();
        }
        
        scanner.close();
    }
}