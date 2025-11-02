package cn.javat.javaLearn.experiment4.controller.Impl;

import cn.javat.javaLearn.experiment4.config.ServiceFactory;
import cn.javat.javaLearn.experiment4.controller.OrderController;
import cn.javat.javaLearn.experiment4.controller.VehicleController;
import cn.javat.javaLearn.experiment4.entity.UserEntity;
import cn.javat.javaLearn.experiment4.entity.Vehicles.CommercialVehicleEntity;
import cn.javat.javaLearn.experiment4.entity.Vehicles.PassengerVehicleEntity;
import cn.javat.javaLearn.experiment4.entity.Vehicles.VehicleEntity;
import cn.javat.javaLearn.experiment4.service.Impl.VehicleServiceImpl;
import cn.javat.javaLearn.experiment4.service.OrderService;
import cn.javat.javaLearn.experiment4.service.UserService;
import cn.javat.javaLearn.experiment4.service.VehicleService;
import cn.javat.javaLearn.experiment4.utils.AppUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class VehicleControllerImpl implements VehicleController {

    private UserEntity currentUser = null;
    private final Scanner scanner = new Scanner(System.in);
    private VehicleService vehicleService;
    private OrderController orderController;

    public VehicleControllerImpl() {
        this.vehicleService = ServiceFactory.getVehicleService();
        this.orderController = new OrderControllerImpl();
    }

    public VehicleControllerImpl(OrderController orderController) {
        this.vehicleService = ServiceFactory.getVehicleService();
        this.orderController = orderController;
    }
    
    public VehicleControllerImpl(VehicleService vehicleService, OrderController orderController) {
        this.vehicleService = vehicleService;
        this.orderController = orderController;
    }

    @Override
    public void setCurrentUser(UserEntity user) {
        this.currentUser = user;
    }

    @Override
    public UserEntity getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void startUp() {
        if (currentUser == null) {
            AppUtils.print("请先登录！");
        }
        if (currentUser.getUserEmail().equals(ADMIN_EMAIL)) {
            adminPanel();
        } else {
            userPanel();
        }

    }

    @Override
    public void userPanel() {
        if (currentUser == null) {
            AppUtils.print("请先登录！");
            return;
        }
        while (currentUser != null) {
            AppUtils.printDoubleLine();
            AppUtils.print("欢迎用户：%s", currentUser.getUserName());
            AppUtils.printLine();
            AppUtils.print("1. 购买车辆");
            AppUtils.print("2. 查询订单");
            AppUtils.print("3. 退出系统");
            switch (scanner.nextInt()) {
                case 1:
                    buyVehicle();
                    break;
                case 2:
                    orderController.printOrdersByUserId(currentUser.getUserId());
                    break;
                case 3:
                    currentUser = null;
                    return;
                default:
                    AppUtils.print("请输入正确的选项！");
            }
        }
    }

    //    TODO：还未Debug
    @Override
    public void buyVehicle() {
        if (currentUser == null) {
            AppUtils.print("请先登录！");
            return;
        }
        ArrayList<VehicleEntity> vehicles = vehicleService.selectAllVehicle();
        AppUtils.printDoubleLine();
        AppUtils.print("车辆列表：");
        AppUtils.printLine();
        printAllVehicle(vehicles);
        AppUtils.printLine();
        VehicleEntity vehicle = null;
        AppUtils.print("请输入购买车辆ID");
        vehicle = vehicleService.selectVehicle(scanner.nextLong());
        while (vehicle == null) {
            AppUtils.print("车辆不存在！");
            AppUtils.print("请输入车辆ID：");
            vehicle = vehicleService.selectVehicle(scanner.nextLong());
        }
        if (vehicle.getVehicleStock() <= 0) {
            AppUtils.print("车辆已售完！");
            return;
        }
        int buyCount = 0;
        while (buyCount <= 0) {
            AppUtils.print("请输入购买数量：");
            buyCount = scanner.nextInt();
            if (buyCount <= 0) {
                AppUtils.print("请输入正确的数量！");
            }
        }
//        计算总价
        double totalPrice = vehicle.getVehiclePrice() * buyCount;
        long timeStamp = System.currentTimeMillis();
        OrderEntity order = new OrderEntity(
                timeStamp,
                currentUser.getUserId(),
                vehicle.getVehicleId(),
                buyCount,
                totalPrice,
                timeStamp
        );

        AppUtils.print("是否确认购买？(y/n)");
        String confirm = scanner.next();
        if (confirm.equals("y")) {
            int result = vehicleService.buyVehicle(order);
            switch (result) {
                case 0:
                    AppUtils.print("购买成功！");
                    break;
                case -1:
                    AppUtils.print("车辆不存在！");
                    return;
                case -2:
                    AppUtils.print("用户不存在！");
                    break;
                case -3:
                    AppUtils.print("库存异常！");
                    break;
                default:
                    AppUtils.print("购买失败！");
                    break;
            }
        } else {
            AppUtils.print("取消购买！");
        }
    }

    @Override
    public void adminPanel() {
        while (currentUser != null) {
            ArrayList<VehicleEntity> vehicles = vehicleService.selectAllVehicle();
            AppUtils.printDoubleLine();
            if (vehicles == null || vehicles.isEmpty()) {
                AppUtils.print("暂无车辆！");
                AppUtils.printLine();
                AppUtils.print("1. 添加车辆");
                AppUtils.print("2. 退出管理");
                AppUtils.print("请选择操作:");
                switch (scanner.nextInt()) {
                    case 1:
                        addVehicle();
                        break;
                    case 2:
                        currentUser = null;
                        return;
                    default:
                        AppUtils.print("请输入正确的选项！");
                }
            } else {
                AppUtils.print("以下为当前存在车辆:");
                AppUtils.printLine();
                printAllVehicle(vehicles);
                AppUtils.printLine();
                AppUtils.print("1. 添加车辆");
                AppUtils.print("2. 删除车辆");
                AppUtils.print("3. 修改车辆");
                AppUtils.print("4. 退出管理");
                AppUtils.print("请选择操作:");
                switch (scanner.nextInt()) {
                    case 1:
                        addVehicle();
                        break;
                    case 2:
                        deleteVehicle();
                        break;
                    case 3:
                        updateVehicle();
                        break;
                    case 4:
                        currentUser = null;
                        return;
                    default:
                        AppUtils.print("请输入正确的选项！");
                }
            }
        }
    }

    @Override
    public void addVehicle() {
        VehicleEntity newVehicle = null;
        String vehicleType = "";
        while (true) {
            if (newVehicle == null) {
                AppUtils.print("设置车辆类型（乘/商)");
                String type = scanner.next();
                switch (type) {
                    case "乘":
                        vehicleType = "passenger";
                        newVehicle = new PassengerVehicleEntity();
                        newVehicle.setVehicleType(vehicleType);
                        break;
                    case "商":
                        vehicleType = "commercial";
                        newVehicle = new CommercialVehicleEntity();
                        newVehicle.setVehicleType(vehicleType);
                        break;
                    default:
                        AppUtils.print("请输入正确的车辆类型！");
                        continue;
                }
            }
            AppUtils.print("1. 设置品牌：%s", newVehicle.getVehicleBrand() != null ? newVehicle.getVehicleBrand() : "");
            AppUtils.print("2. 设置型号: %s", newVehicle.getVehicleModel() != null ? newVehicle.getVehicleModel() : "");
            AppUtils.print("3. 设置单价（万元/辆）: %.2f", newVehicle.getVehiclePrice());
            AppUtils.print("4. 设置库存（辆）: %d", newVehicle.getVehicleStock());
//            设置特定值：
            if (vehicleType.equals("commercial")) {
                AppUtils.print("5. 设置载重（吨）: %.2f", ((CommercialVehicleEntity) newVehicle).getLoadCapacity());
                AppUtils.print("6. 设置容积（立方米）: %.2f", ((CommercialVehicleEntity) newVehicle).getCargoVolume());
            } else {
                AppUtils.print("5. 设置载客量（座）: %d", ((PassengerVehicleEntity) newVehicle).getSeatCount());
                AppUtils.print("6. 设置动力类型: %s", ((PassengerVehicleEntity) newVehicle).getFuelType() != null ? ((PassengerVehicleEntity) newVehicle).getFuelType() : "");
            }
            AppUtils.print("7. 预览结果");
            AppUtils.print("8. 取消添加");

            switch (scanner.nextInt()) {
                case 1:
                    AppUtils.print("请输入车辆品牌:");
                    newVehicle.setVehicleBrand(scanner.next());
                    break;
                case 2:
                    AppUtils.print("请输入车辆型号:");
                    newVehicle.setVehicleModel(scanner.next());
                    break;
                case 3:
                    AppUtils.print("请输入车辆单价（万元/辆）:");
                    newVehicle.setVehiclePrice(scanner.nextDouble());
                    break;
                case 4:
                    AppUtils.print("请输入车辆库存（辆）:");
                    newVehicle.setVehicleStock(scanner.nextInt());
                    break;
                case 5:
                    if (vehicleType.equals("commercial")) {
                        AppUtils.print("请输入车辆载重（吨）:");
                        ((CommercialVehicleEntity) newVehicle).setLoadCapacity(scanner.nextDouble());
                    } else {
                        AppUtils.print("请输入车辆载客量（座）:");
                        ((PassengerVehicleEntity) newVehicle).setSeatCount(scanner.nextInt());
                    }
                    break;
                case 6:
                    if (vehicleType.equals("commercial")) {
                        AppUtils.print("请输入车辆容积（立方米）:");
                        ((CommercialVehicleEntity) newVehicle).setCargoVolume(scanner.nextDouble());
                    } else {
                        AppUtils.print("请输入车辆动力类型:");
                        ((PassengerVehicleEntity) newVehicle).setFuelType(scanner.next());
                    }
                    break;
                case 7:
                    printVehicle(newVehicle);
                    AppUtils.print("是否确定添加车辆？(y/n)");
                    if (scanner.next().equals("y")) {
                        // 设置车辆ID为0，确保是新车辆
                        newVehicle.setVehicleId(0);
                        if (vehicleService.insertVehicle(newVehicle) > -1) {
                            AppUtils.print("添加成功！");
                        } else {
                            AppUtils.print("添加失败！");
                        }
                        return;
                    } else {
                        AppUtils.print("取消添加！");
                    }
                    break;
                case 8:
                    AppUtils.print("未保存的信息将会丢失！确定取消？（y/n）");
                    if (scanner.next().equals("y")) {
                        AppUtils.print("取消添加！");
                        return;
                    }
                    break;
                default:
                    AppUtils.print("请输入正确的选项！");
            }
        }
    }

    /**
     * 删除车辆
     */
    @Override
    public void deleteVehicle() {
        AppUtils.print("请输入要删除的车辆ID:");
        long carId = scanner.nextLong();
        AppUtils.printLine();
        VehicleEntity vehicle = vehicleService.selectVehicle(carId);
        if (vehicle != null) {
            printVehicleInline(vehicle);
            AppUtils.print("确定要删除上述车辆吗？(y/n)");
            if (scanner.next().equals("y")) {
                if (vehicleService.DeleteVehicle(carId) > -1) {
                    AppUtils.print("删除成功！");
                } else {
                    AppUtils.print("删除失败！");
                }
            } else {
                AppUtils.print("取消删除！");
            }
        }
    }

    /**
     * 修改车辆信息
     *
     */
    @Override
    public void updateVehicle() {
        AppUtils.print("请输入修改目标车辆的ID:");
        long carId = scanner.nextLong();
        VehicleEntity vehicle = vehicleService.selectVehicle(carId);
        while (vehicle == null) {
            AppUtils.print("车辆不存在！请重新输入车辆ID:");
            carId = scanner.nextLong();
            vehicle = vehicleService.selectVehicle(carId);
        }
        // 不允许修改车辆类型，品牌，型号
        String vehicleType = vehicle.getVehicleType();
        while (true) {
            AppUtils.printLine();
            AppUtils.print("只读的信息:");
            AppUtils.print("车辆ID: %d", vehicle.getVehicleId());
            AppUtils.print("车辆类型: %s", Objects.equals(vehicle.getVehicleType(), "commercial") ? "商用车" : "乘用车");
            AppUtils.print("车辆品牌: %s", vehicle.getVehicleBrand());
            AppUtils.print("车辆型号: %s", vehicle.getVehicleModel());
            AppUtils.printLine();
            AppUtils.print("可更新的信息：");
            AppUtils.print("3. 设置单价（万元/辆）: %.2f", vehicle.getVehiclePrice());
            AppUtils.print("4. 设置库存（辆）: %d", vehicle.getVehicleStock());

            if (vehicleType.equals("commercial")) {
                AppUtils.print("5. 设置载重（吨）: %.2f", ((CommercialVehicleEntity) vehicle).getLoadCapacity());
                AppUtils.print("6. 设置容积（立方米）: %.2f", ((CommercialVehicleEntity) vehicle).getCargoVolume());
            } else if (vehicleType.equals("passenger")) {
                AppUtils.print("5. 设置载客量（座）: %d", ((PassengerVehicleEntity) vehicle).getSeatCount());
                AppUtils.print("6. 设置动力类型: %s", ((PassengerVehicleEntity) vehicle).getFuelType() != null ? ((PassengerVehicleEntity) vehicle).getFuelType() : "");
            }

            AppUtils.print("7. 保存修改");
            AppUtils.print("8. 取消修改");

            switch (scanner.nextInt()) {
                case 3:
                    AppUtils.print("请输入车辆单价（万元/辆）:");
                    vehicle.setVehiclePrice(scanner.nextDouble());
                    break;
                case 4:
                    AppUtils.print("请输入车辆库存（辆）:");
                    vehicle.setVehicleStock(scanner.nextInt());
                    break;
                case 5:
                    if (vehicleType.equals("commercial")) {
                        AppUtils.print("请输入车辆载重（吨）:");
                        ((CommercialVehicleEntity) vehicle).setLoadCapacity(scanner.nextDouble());
                    } else if (vehicleType.equals("passenger")) {
                        AppUtils.print("请输入车辆载客量（座）:");
                        ((PassengerVehicleEntity) vehicle).setSeatCount(scanner.nextInt());
                    }
                    break;
                case 6:
                    if (vehicleType.equals("commercial")) {
                        AppUtils.print("请输入车辆容积（立方米）:");
                        ((CommercialVehicleEntity) vehicle).setCargoVolume(scanner.nextDouble());
                    } else if (vehicleType.equals("passenger")) {
                        AppUtils.print("请输入车辆动力类型:");
                        ((PassengerVehicleEntity) vehicle).setFuelType(scanner.next());
                    }
                    break;
                case 7:
                    printVehicle(vehicle);
                    AppUtils.print("是否确定修改车辆信息？(y/n)");
                    if (scanner.next().equals("y")) {
                        if (vehicleService.updateVehicle(vehicle) > -1) {
                            AppUtils.print("修改成功！");
                        } else {
                            AppUtils.print("修改失败！");
                        }
                        return;
                    } else {
                        AppUtils.print("取消修改！");
                    }
                    break;
                case 8:
                    AppUtils.print("未保存的修改将会丢失！确定取消？（y/n）");
                    if (scanner.next().equals("y")) {
                        AppUtils.print("取消修改！");
                        return;
                    }
                    break;
                default:
                    AppUtils.print("请输入正确的选项！");
            }
        }
    }


    /**
     * 详细打印车辆信息
     *
     * @param vehicle 车辆
     */
    @Override
    public void printVehicle(VehicleEntity vehicle) {
        AppUtils.print("车辆详细信息:");
        AppUtils.printLine();
        AppUtils.print("车辆ID: %d", vehicle.getVehicleId());
        AppUtils.print("车辆类型: %s", vehicle.getVehicleType());
        AppUtils.print("车辆品牌: %s", vehicle.getVehicleBrand());
        AppUtils.print("车辆型号: %s", vehicle.getVehicleModel());
        AppUtils.print("车辆单价: %.2f万元", vehicle.getVehiclePrice());
        AppUtils.print("车辆库存: %d辆", vehicle.getVehicleStock());

        if (vehicle instanceof CommercialVehicleEntity commercialVehicle) {
            AppUtils.print("载重: %.2f吨", commercialVehicle.getLoadCapacity());
            AppUtils.print("容积: %.2f立方米", commercialVehicle.getCargoVolume());
        } else if (vehicle instanceof PassengerVehicleEntity passengerVehicle) {
            AppUtils.print("座位数: %d座", passengerVehicle.getSeatCount());
            AppUtils.print("动力类型: %s", passengerVehicle.getFuelType());
        }
        AppUtils.printLine();
    }

    /**
     * 行内打印车辆信息
     *
     * @param vehicle 车辆
     */
    @Override
    public void printVehicleInline(VehicleEntity vehicle) {
        AppUtils.print(vehicle.toString());
    }

    @Override
    public void printAllVehicle(ArrayList<VehicleEntity> vehicles) {
        for (VehicleEntity vehicle : vehicles) {
            printVehicleInline(vehicle);
        }
    }
}