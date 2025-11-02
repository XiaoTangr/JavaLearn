package cn.javat.javaLearn.experiment4.controller.Impl;

import cn.javat.javaLearn.experiment4.config.ServiceFactory;
import cn.javat.javaLearn.experiment4.controller.OrderController;

import cn.javat.javaLearn.experiment4.controller.UserController;
import cn.javat.javaLearn.experiment4.entity.OrderEntity;
import cn.javat.javaLearn.experiment4.entity.UserEntity;
import cn.javat.javaLearn.experiment4.entity.Vehicles.CommercialVehicleEntity;
import cn.javat.javaLearn.experiment4.entity.Vehicles.PassengerVehicleEntity;
import cn.javat.javaLearn.experiment4.entity.Vehicles.VehicleEntity;
import cn.javat.javaLearn.experiment4.service.Impl.OrderServiceImpl;
import cn.javat.javaLearn.experiment4.service.Impl.UserServiceImpl;
import cn.javat.javaLearn.experiment4.service.Impl.VehicleServiceImpl;
import cn.javat.javaLearn.experiment4.service.OrderService;
import cn.javat.javaLearn.experiment4.service.UserService;
import cn.javat.javaLearn.experiment4.service.VehicleService;
import cn.javat.javaLearn.experiment4.utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderControllerImpl implements OrderController {
    
    private UserEntity currentUser = null;
    private final Scanner scanner = new Scanner(System.in);
    private OrderService orderService;
    private UserService userService;
    private VehicleService vehicleService;

    private UserController userController;
    
    public OrderControllerImpl() {
        // 使用工厂模式获取服务实例，避免重复初始化
        this.orderService = ServiceFactory.getOrderService();
        this.userService = ServiceFactory.getUserService();
        this.vehicleService = ServiceFactory.getVehicleService();
    }
    
    public OrderControllerImpl(OrderService orderService, UserService userService, VehicleService vehicleService) {
        // 依赖注入构造函数，允许外部控制服务实现
        this.orderService = orderService;
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    @Override
    public void printOrderInline(OrderEntity order) {
        AppUtils.print(order.toString());
    }

    @Override
    public void printAllOrder(ArrayList<OrderEntity> orders) {
        for (OrderEntity order : orders) {
            printOrderInline(order);
        }
    }

    @Override
    public void printOrder(OrderEntity order) {
        UserEntity user = userService.selectUser(order.getUserId());
        VehicleEntity vehicle = vehicleService.selectVehicle(order.getVehicleId());

        if (user == null || vehicle == null) {
            AppUtils.print("用户或车辆不存在");
            return;
        }
        AppUtils.printDoubleLine();
        AppUtils.print("订单详细信息");
        AppUtils.printLine();
        AppUtils.print("# %d @ %s - %s", vehicle.getVehicleId(), vehicle.getVehicleBrand(), vehicle.getVehicleModel());
        if (vehicle instanceof CommercialVehicleEntity commercialVehicle) {
            AppUtils.print("# 商用 @ %.2f吨 - %.2f立方", commercialVehicle.getLoadCapacity(), commercialVehicle.getCargoVolume());
        }
        if (vehicle instanceof PassengerVehicleEntity passengerVehicle) {
            AppUtils.print("# 乘用 @ %d座 - %s", passengerVehicle.getSeatCount(), passengerVehicle.getFuelType());
        }
        AppUtils.print("# 购买数量：%d辆", order.getBuyCount());
        AppUtils.print("# 单价：%.2f元/辆", vehicle.getVehiclePrice());
        AppUtils.print("# 总价：%.2f元", order.getTotalPrice());
        AppUtils.printLine();
        AppUtils.print("# 订单ID：" + order.getOrderId());
        AppUtils.print("# 购买人：%s - %s", user.getUserName(), user.getUserEmail());
        AppUtils.print("# 创建于：%s", new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(order.getCreateTime()));
        AppUtils.printDoubleLine();
    }

    @Override
    public void printOrdersByUserId(long userId) {
        ArrayList<OrderEntity> orders = orderService.selectByUserId(userId);
        AppUtils.printDoubleLine();
        if (orders == null || orders.isEmpty()) {
            AppUtils.print("暂无订单！");
        } else {
            AppUtils.print("下列订单所属用户ID为：%s", userId);
            AppUtils.printLine();
            printAllOrder(orders);
        }
    }


    @Override
    public void printOrdersByVehicleId(long vehicleId) {
        ArrayList<OrderEntity> orders = orderService.selectByVehicleId(vehicleId);
        AppUtils.printDoubleLine();
        if (orders == null || orders.isEmpty()) {
            AppUtils.print("暂无订单！");
        } else {
            AppUtils.print("下列订单所属车辆ID为：%s", vehicleId);
            AppUtils.printLine();
            printAllOrder(orders);
        }
    }
}