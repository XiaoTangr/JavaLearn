package cn.javat.javaLearn.experiment5.service;

import cn.javat.javaLearn.experiment5.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 购车任务类 - 实现Runnable接口
 * 用于执行用户购车操作
 */
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseTask implements Runnable {

    private CarInventory inventory;
    private User user;
    private PurchaseMethod method;

    @Override
    public void run() {
        switch (method) {
            case UNSAFE:
                inventory.purchaseUnsafe(user.getName());
                break;
            case SAFE_SYNC:
                inventory.purchaseSafeSync(user.getName());
                break;
            case SAFE_LOCK:
                inventory.purchaseSafeLock(user.getName());
                break;
        }
    }

    public enum PurchaseMethod {
        UNSAFE,
        SAFE_SYNC,
        SAFE_LOCK
    }
}