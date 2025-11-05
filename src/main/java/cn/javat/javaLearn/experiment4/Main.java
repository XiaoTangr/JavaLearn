package cn.javat.javaLearn.experiment4;

import cn.javat.javaLearn.experiment4.utils.AppUtils;

import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        try {
            // 使用反射获取UserControllerImpl类
            Class<?> userControllerClass = Class.forName("cn.javat.javaLearn.experiment4.controller.Impl.UserControllerImpl");

            // 创建实例
            Object userController = userControllerClass.getDeclaredConstructor().newInstance();

            // 获取startUp方法
            Method startUpMethod = userControllerClass.getMethod("startUp");
            // 调用startUp方法
            startUpMethod.invoke(userController);
        } catch (Exception e) {
            AppUtils.print("启动失败: " + e);
        }
    }
}