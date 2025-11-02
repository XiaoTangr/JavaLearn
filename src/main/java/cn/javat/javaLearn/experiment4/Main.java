package cn.javat.javaLearn.experiment4;

import cn.javat.javaLearn.experiment4.config.ServiceFactory;
import cn.javat.javaLearn.experiment4.controller.Impl.OrderControllerImpl;
import cn.javat.javaLearn.experiment4.controller.Impl.UserControllerImpl;
import cn.javat.javaLearn.experiment4.controller.Impl.VehicleControllerImpl;
import cn.javat.javaLearn.experiment4.controller.OrderController;
import cn.javat.javaLearn.experiment4.controller.UserController;
import cn.javat.javaLearn.experiment4.controller.VehicleController;

public class Main {

    static UserController userController;

    public static void main(String[] args) {
        // 使用工厂模式和服务注入的方式初始化控制器
        userController = new UserControllerImpl(
                new VehicleControllerImpl(
                        new OrderControllerImpl()
                )
        );
        userController.startUp();
    }
}