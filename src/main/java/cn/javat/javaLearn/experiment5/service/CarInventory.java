package cn.javat.javaLearn.experiment5.service;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarInventory {

    @Getter
    private int stock;
    private final Lock lock = new ReentrantLock();

    public CarInventory(int initialStock) {
        this.stock = initialStock;
    }

    /**
     * 线程不安全的购买方法 - 用于演示问题
     *
     * @param userName 用户名称
     * @return 购买是否成功
     */
    public boolean purchaseUnsafe(String userName) {
        if (stock > 0) {
            System.out.println(userName + " 正在购买车辆...");
            // 模拟处理时间
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            stock--;
            System.out.println(userName + " 购买成功，剩余库存: " + stock);
            return true;
        } else {
            System.out.println(userName + " 购买失败，库存不足");
            return false;
        }
    }

    /**
     * 线程安全的购买方法 - 使用synchronized
     *
     * @param userName 用户名称
     * @return 购买是否成功
     */
    public synchronized boolean purchaseSafeSync(String userName) {
        if (stock > 0) {
            System.out.println(userName + " 正在购买车辆...");
            // 模拟处理时间
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            stock--;
            System.out.println(userName + " 购买成功，剩余库存: " + stock);
            return true;
        } else {
            System.out.println(userName + " 购买失败，库存不足");
            return false;
        }
    }

    /**
     * 线程安全的购买方法 - 使用Lock
     *
     * @param userName 用户名称
     * @return 购买是否成功
     */
    public boolean purchaseSafeLock(String userName) {
        lock.lock();
        try {
            if (stock > 0) {
                System.out.println(userName + " 正在购买车辆...");
                // 模拟处理时间
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                stock--;
                System.out.println(userName + " 购买成功，剩余库存: " + stock);
                return true;
            } else {
                System.out.println(userName + " 购买失败，库存不足");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 线程不安全的库存增加方法 - 用于演示问题
     *
     * @param amount    增加数量
     * @param adminName 管理员名称
     */
    public void addStockUnsafe(int amount, String adminName) {
        System.out.println(adminName + " 正在增加库存 " + amount + " 辆...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        stock += amount;
        System.out.println(adminName + " 增加库存完成，当前库存: " + stock);
    }

    /**
     * 线程不安全的库存减少方法 - 用于演示问题
     *
     * @param amount    减少数量
     * @param adminName 管理员名称
     */
    public void reduceStockUnsafe(int amount, String adminName) {
        System.out.println(adminName + " 正在减少库存 " + amount + " 辆...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        stock -= amount;
        System.out.println(adminName + " 减少库存完成，当前库存: " + stock);
    }

    /**
     * 线程安全的库存增加方法 - 使用synchronized
     *
     * @param amount    增加数量
     * @param adminName 管理员名称
     */
    public synchronized void addStockSafe(int amount, String adminName) {
        System.out.println(adminName + " 正在增加库存 " + amount + " 辆...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        stock += amount;
        System.out.println(adminName + " 增加库存完成，当前库存: " + stock);
    }

    /**
     * 线程安全的库存减少方法 - 使用synchronized
     *
     * @param amount    减少数量
     * @param adminName 管理员名称
     */
    public synchronized void reduceStockSafe(int amount, String adminName) {
        System.out.println(adminName + " 正在减少库存 " + amount + " 辆...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        stock -= amount;
        System.out.println(adminName + " 减少库存完成，当前库存: " + stock);
    }

}