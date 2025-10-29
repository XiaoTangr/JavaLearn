package cn.javat.javaLearn.experiment4.controller;

public interface VehicleController {
    void startUp(); // 启动

    //    —————————————————— 普通用户 ——————————————————————
    void userPanel();

    void buyVehicle(); // 购买车辆

    //    —————————————————— 管 理 员 ——————————————————————

    void adminPanel();  // 管理员面板

    void addVehicle(); // 添加车辆

    void deleteVehicle();

    void updateVehicle();

    //    —————————————————— 公    共 ——————————————————————
    void queryVehicle();

    void queryAllVehicle();

    void printVehicle();

    void printVehicleInline();
    
    void printAllVehicle();

    void printAllOrderInline();

    void printAllOrder();

    void printOrder();
}
