package cn.javat.javaLearn.experiment5;

import cn.javat.javaLearn.experiment5.entity.Car;
import cn.javat.javaLearn.experiment5.entity.User;
import cn.javat.javaLearn.experiment5.service.CarInventory;
import cn.javat.javaLearn.experiment5.service.PurchaseTask;
import cn.javat.javaLearn.experiment5.service.StockManagementTask;

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程实验主类
 * 演示线程创建、同步、通信等基本操作
 */
public class Main {
    /**
     * 主方法
     *
     * @param args 命令行参数
     * @throws InterruptedException 线程中断异常
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 多线程实验 ===\n");
        // 创建车辆实例
        new Car("特斯拉", "Model S");
        // 1. 模拟VIP用户优先购车
        System.out.println("--- 1. 模拟VIP用户优先购车 ---");
        demonstrateVipPriority();
        Thread.sleep(1000); // 暂停一下再执行下一个实验
        // 2. 模拟车辆库存不足卖车的线程不安全问题
        System.out.println("\n--- 2. 模拟车辆库存不足卖车的线程不安全问题 ---");
        demonstrateUnsafePurchase();
        Thread.sleep(1000); // 暂停一下再执行下一个实验
        // 3. 解决不同管理员增减库存线程不安全问题
        System.out.println("\n--- 3. 解决不同管理员增减库存线程不安全问题 ---");
        demonstrateSafeStockManagement();
    }

    /**
     * 演示VIP用户优先购车
     *
     * @throws InterruptedException 线程中断异常
     */
    private static void demonstrateVipPriority() throws InterruptedException {
        CarInventory inventory = new CarInventory(2);

        // 创建不同类型用户
        User regularUser1 = new User("普通用户A", User.UserType.REGULAR);
        User regularUser2 = new User("普通用户B", User.UserType.REGULAR);
        User vipUser = new User("VIP用户", User.UserType.VIP);
        User svipUser = new User("SVIP用户", User.UserType.SVIP);

        // 创建购买任务 - 普通用户先进入队列
        Thread regularThread1 = new Thread(new PurchaseTask(inventory, regularUser1, PurchaseTask.PurchaseMethod.SAFE_SYNC));
        Thread regularThread2 = new Thread(new PurchaseTask(inventory, regularUser2, PurchaseTask.PurchaseMethod.SAFE_SYNC));
        Thread vipThread = new Thread(new PurchaseTask(inventory, vipUser, PurchaseTask.PurchaseMethod.SAFE_SYNC));
        Thread svipThread = new Thread(new PurchaseTask(inventory, svipUser, PurchaseTask.PurchaseMethod.SAFE_SYNC));

        // 启动线程，但让SVIP/VIP用户稍后启动以模拟优先插队
        regularThread1.start();
        regularThread2.start();

        // 模拟VIP用户插队效果
        Thread.sleep(10);
        vipThread.start();

        Thread.sleep(10);
        svipThread.start();

        // 等待所有线程完成
        regularThread1.join();
        regularThread2.join();
        vipThread.join();
        svipThread.join();
    }

    /**
     * 演示线程不安全的购买行为
     *
     * @throws InterruptedException 线程中断异常
     */
    private static void demonstrateUnsafePurchase() throws InterruptedException {
        // 初始化库存为少量
        CarInventory inventory = new CarInventory(3);
        List<Thread> threads = new ArrayList<>();

        // 创建多个用户同时购车
        for (int i = 1; i <= 5; i++) {
            User user = new User("用户" + i, User.UserType.REGULAR);
            Thread thread = new Thread(new PurchaseTask(inventory, user, PurchaseTask.PurchaseMethod.UNSAFE));
            threads.add(thread);
        }

        // 同时启动所有线程
        System.out.println("开始同时购车（线程不安全版本）:");
        for (Thread thread : threads) {
            thread.start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("最终库存: " + inventory.getStock());
    }

    /**
     * 演示线程安全的库存管理
     *
     * @throws InterruptedException 线程中断异常
     */
    private static void demonstrateSafeStockManagement() throws InterruptedException {
        // 初始化库存
        CarInventory inventory = new CarInventory(10);

        // 创建线程进行不安全操作
        System.out.println("线程不安全的库存操作:");
        Thread adminA_add = new Thread(new StockManagementTask(inventory, 5, "管理员A", StockManagementTask.Operation.ADD_UNSAFE));
        Thread adminB_reduce = new Thread(new StockManagementTask(inventory, 3, "管理员B", StockManagementTask.Operation.REDUCE_UNSAFE));
        Thread adminC_add = new Thread(new StockManagementTask(inventory, 2, "管理员C", StockManagementTask.Operation.ADD_UNSAFE));
        Thread adminD_reduce = new Thread(new StockManagementTask(inventory, 4, "管理员D", StockManagementTask.Operation.REDUCE_UNSAFE));

        // 启动所有线程
        adminA_add.start();
        adminB_reduce.start();
        adminC_add.start();
        adminD_reduce.start();

        // 等待所有线程完成
        adminA_add.join();
        adminB_reduce.join();
        adminC_add.join();
        adminD_reduce.join();

        System.out.println("经过不安全操作后的库存: " + inventory.getStock());

        // 重置库存
        inventory = new CarInventory(10);

        // 创建线程进行安全操作
        System.out.println("\n线程安全的库存操作:");
        Thread adminA_add_safe = new Thread(new StockManagementTask(inventory, 5, "管理员A", StockManagementTask.Operation.ADD_SAFE));
        Thread adminB_reduce_safe = new Thread(new StockManagementTask(inventory, 3, "管理员B", StockManagementTask.Operation.REDUCE_SAFE));
        Thread adminC_add_safe = new Thread(new StockManagementTask(inventory, 2, "管理员C", StockManagementTask.Operation.ADD_SAFE));
        Thread adminD_reduce_safe = new Thread(new StockManagementTask(inventory, 4, "管理员D", StockManagementTask.Operation.REDUCE_SAFE));

        // 启动所有线程
        adminA_add_safe.start();
        adminB_reduce_safe.start();
        adminC_add_safe.start();
        adminD_reduce_safe.start();

        // 等待所有线程完成
        adminA_add_safe.join();
        adminB_reduce_safe.join();
        adminC_add_safe.join();
        adminD_reduce_safe.join();

        System.out.println("经过安全操作后的库存: " + inventory.getStock());
    }
}