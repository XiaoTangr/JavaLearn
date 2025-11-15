package cn.javat.javaLearn.experiment5.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class StockManagementTask implements Runnable {

    private CarInventory inventory;
    private int amount;
    private String adminName;
    private Operation operation;

    @Override
    public void run() {
        switch (operation) {
            case ADD_UNSAFE:
                inventory.addStockUnsafe(amount, adminName);
                break;
            case REDUCE_UNSAFE:
                inventory.reduceStockUnsafe(amount, adminName);
                break;
            case ADD_SAFE:
                inventory.addStockSafe(amount, adminName);
                break;
            case REDUCE_SAFE:
                inventory.reduceStockSafe(amount, adminName);
                break;
        }
    }

    public enum Operation {
        ADD_UNSAFE,
        REDUCE_UNSAFE,
        ADD_SAFE,
        REDUCE_SAFE
    }
}