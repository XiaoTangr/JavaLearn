package cn.javat.javaLearn.experiment4.config;

import cn.javat.javaLearn.experiment4.service.OrderService;
import cn.javat.javaLearn.experiment4.service.UserService;
import cn.javat.javaLearn.experiment4.service.VehicleService;
import cn.javat.javaLearn.experiment4.service.Impl.OrderServiceImpl;
import cn.javat.javaLearn.experiment4.service.Impl.UserServiceImpl;
import cn.javat.javaLearn.experiment4.service.Impl.VehicleServiceImpl;

/**
 * 服务工厂类，用于创建和管理服务实例（单例模式）
 */
public class ServiceFactory {
    private static OrderService orderService;
    private static UserService userService;
    private static volatile VehicleService vehicleService;

    private ServiceFactory() {
        // 私有构造函数，防止外部实例化
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            synchronized (ServiceFactory.class) {
                if (orderService == null) {
                    orderService = new OrderServiceImpl();
                }
            }
        }
        return orderService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            synchronized (ServiceFactory.class) {
                if (userService == null) {
                    userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }

    public static VehicleService getVehicleService() {
        if (vehicleService == null) {
            synchronized (ServiceFactory.class) {
                if (vehicleService == null) {
                    vehicleService = new VehicleServiceImpl();
                }
            }
        }
        return vehicleService;
    }
}