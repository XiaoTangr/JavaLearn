package cn.javat.javaLearn.experiment4;

import cn.javat.javaLearn.experiment4.controller.Impl.UserControllerImpl;
import cn.javat.javaLearn.experiment4.controller.UserController;

public class Main {

    static UserController userController = new UserControllerImpl();

    public static void main(String[] args) {
        userController.startUp();
    }
}