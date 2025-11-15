package cn.javat.javaLearn.experiment4;

import cn.javat.javaLearn.experiment4.controller.Impl.UserControllerImpl;
import cn.javat.javaLearn.experiment4.controller.UserController;

public class Main {

    public static void main(String[] args) {
        UserController userController = new UserControllerImpl();
        userController.startUp();
    }
}