package cn.javat.javaLearn.experiment4.controller;

public interface UserController {
    void startUp(); // 启动

    //    —————————————————— 普通用户 ——————————————————————
    void userPanel(); // 用户面板

    //    —————————————————— 管 理 员 ——————————————————————
    void adminPanel();  // 管理员面板

    //    —————————————————— 公    共 ——————————————————————
    void login(); // 登录

    void register(); // 注册

    void logout(); // 登出

    void updateUser();

    void queryUser();

    void printUser();

    void printUserInline();

    void printAllUser();

}
