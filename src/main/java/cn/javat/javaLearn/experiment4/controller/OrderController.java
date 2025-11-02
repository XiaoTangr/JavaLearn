package cn.javat.javaLearn.experiment4.controller;

import cn.javat.javaLearn.experiment4.entity.OrderEntity;
import cn.javat.javaLearn.experiment4.entity.UserEntity;

import java.util.ArrayList;

/**
 * 订单控制器接口
 * <p>
 * 定义订单相关操作的接口，用于处理订单信息的展示逻辑
 * </p>
 */
public interface OrderController {

    /**
     * 以行内格式打印订单信息
     *
     * @param order 订单实体对象
     */
    void printOrderInline(OrderEntity order);

    /**
     * 打印所有订单信息（行内格式）
     *
     * @param orders 订单列表
     */
    void printAllOrder(ArrayList<OrderEntity> orders);

    /**
     * 以详细格式打印订单信息
     *
     * @param order 订单实体对象
     */
    void printOrder(OrderEntity order);

    /**
     * 根据用户ID打印该用户的所有订单
     *
     * @param userId 用户ID
     */
    void printOrdersByUserId(long userId);

    void printOrdersByVehicleId(long vehicleId);
}