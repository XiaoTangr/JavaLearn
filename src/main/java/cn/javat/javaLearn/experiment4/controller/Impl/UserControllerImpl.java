package cn.javat.javaLearn.experiment4.controller.Impl;

import cn.javat.javaLearn.experiment4.config.ServiceFactory;
import cn.javat.javaLearn.experiment4.controller.UserController;
import cn.javat.javaLearn.experiment4.controller.VehicleController;
import cn.javat.javaLearn.experiment4.entity.UserEntity;
import cn.javat.javaLearn.experiment4.service.UserService;
import cn.javat.javaLearn.experiment4.service.Impl.UserServiceImpl;
import cn.javat.javaLearn.experiment4.service.VehicleService;
import cn.javat.javaLearn.experiment4.utils.AppUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class UserControllerImpl implements UserController {

    public static final String ADMIN_EMAIL = "admin@javat.cn";
    private UserEntity currentUser = null;
    private final Scanner scanner = new Scanner(System.in);
    private UserService userService;
    private VehicleController vehicleController;

    public UserControllerImpl() {
        this.userService = ServiceFactory.getUserService();
    }

    public UserControllerImpl(VehicleController vehicleController) {
        this();
        this.vehicleController = vehicleController;
    }
    
    public UserControllerImpl(UserService userService, VehicleController vehicleController) {
        this.userService = userService;
        this.vehicleController = vehicleController;
    }

    //    入口
    @Override
    public void startUp() {
        vehicleController = new VehicleControllerImpl();
        userService = new UserServiceImpl();
        if (currentUser != null) {
            if (Objects.equals(currentUser.getUserEmail(), ADMIN_EMAIL)) {
                adminPanel();
            } else {
                userPanel();
            }
            return;
        }
        while (currentUser == null) {
            AppUtils.printDoubleLine();
            AppUtils.print("欢迎访问汽车交易系统！");
            AppUtils.printLine();
            AppUtils.print("1. 登录");
            AppUtils.print("2. 注册");
            AppUtils.print("3. 退出");
            AppUtils.printLine();
            AppUtils.print("请选择：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    AppUtils.print("无效的选择！请重新选择。");
            }
        }
    }

    //     —————————————————— 普通用户 ——————————————————————
    //     普通用户面板
    @Override
    public void userPanel() {
        if (currentUser == null) {
            AppUtils.print("请先登录！");
            login();
        }
        while (currentUser != null) {
            AppUtils.printDoubleLine();
            AppUtils.print("欢迎用户：%s", currentUser.getUserName());
            AppUtils.printLine();
            AppUtils.print("1. 修改当前用户信息");
            AppUtils.print("2. 进入车辆交易系统");
            AppUtils.print("3. 退出登录");
            AppUtils.printLine();
            AppUtils.print("请选择：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    updateUser(currentUser);
                    break;
                case 2:
                    vehicleController.setCurrentUser(currentUser);
                    vehicleController.startUp();
                    break;
                case 3:
                    logout();
                    break;
                default:
                    AppUtils.print("无效的选择！请重新选择。");
            }
        }
    }

    //     —————————————————— 管 理 员 ——————————————————————
    //   管理员面板
    @Override
    public void adminPanel() {
        if (currentUser == null) {
            AppUtils.print("请先登录！");
            login();
        }
        if (!currentUser.getUserEmail().equals(ADMIN_EMAIL)) {
            AppUtils.print("您没有权限访问此功能！");
            return;
        }
        while (currentUser != null) {
            AppUtils.printDoubleLine();
            AppUtils.print("欢迎管理员：%s", currentUser.getUserName());
            AppUtils.printLine();
            AppUtils.print("1. 修改当前用户信息");
            AppUtils.print("2. 进入用户管理系统");
            AppUtils.print("3. 进入车辆管理系统");
            AppUtils.print("4. 退出登录");
            AppUtils.printLine();
            AppUtils.print("请选择：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    updateUser(currentUser);
                    break;
                case 2:
                    updateUserWithAdmin();
                    break;
                case 3:
                    if (vehicleController != null) {
                        vehicleController.setCurrentUser(currentUser);
                        vehicleController.startUp();
                    }
                    break;
                case 4:
                    logout();
                    return;
                default:
                    AppUtils.print("无效的选择！请重新选择。");
            }
        }
    }

    //     —————————————————— 公    共 ——————————————————————
    //     登录
    @Override
    public void login() {
        while (currentUser == null) {
            AppUtils.print("请输入用户邮箱：");
            String userEmail = scanner.next();
            AppUtils.print("请输入用户密码：");
            String userPassword = scanner.next();
            Object result = userService.login(userEmail, userPassword);
            if (result instanceof UserEntity) {
                currentUser = (UserEntity) result;
                startUp();
            } else {
                int res = (int) result;
                switch (res) {
                    case -1:
                        AppUtils.print("账号密码错误！请重新输入。");
                        break;
                    case -2:
                        AppUtils.print("账号不存在！请重新输入。");
                        break;
                    case -3:
                        AppUtils.print("账号被禁用！请联系管理员。");
                        break;
                }
            }
        }
    }

    //     注册
    @Override
    public void register() {
        while (currentUser == null) {
//            时间戳为用户ID
            long userId = System.currentTimeMillis();
            AppUtils.print("请输入用户邮箱：");
            String userEmail = scanner.next();
            AppUtils.print("请输入用户名：");
            String userName = scanner.next();
            AppUtils.print("请输入用户密码：");
            String userPassword = scanner.next();
            AppUtils.print("请再次输入密码：");
            String userPassword2 = scanner.next();
            while (!userPassword.equals(userPassword2)) {
                AppUtils.print("两次输入的密码不一致！请重新输入。");
                AppUtils.print("请输入用户密码：");
                userPassword = scanner.next();
                AppUtils.print("请再次输入密码：");
                userPassword2 = scanner.next();
            }
            UserEntity newUser = new UserEntity(userId, userEmail, userName, userPassword, true);
            printUser(newUser);
            AppUtils.print("是否确认注册？(y/n)");
            String choice = scanner.next();
            if (choice.equals("y")) {
                AppUtils.print("注册成功,即将自动登录");
                currentUser = userService.register(newUser);
                startUp();
            } else if (choice.equals("n")) {
                AppUtils.print("取消注册！");
            }
        }
    }

    //     登出
    @Override
    public void logout() {
        currentUser = null;
        AppUtils.print("已退出登录！");
        startUp();
    }

    //    修改用户信息
    @Override
    public void updateUser(UserEntity user) {
        UserEntity modifiedUser = user;
        boolean finishEdit = false;
        while (!finishEdit) {
            AppUtils.printDoubleLine();
            AppUtils.print("修改用户信息（退出：q ）");
            AppUtils.printLine();
            AppUtils.print("用户ID： %s", user.getUserId());
            AppUtils.print("1. 修改用户名（当前设置为：%s）", modifiedUser.getUserName());
            AppUtils.print("2. 修改邮箱（当前设置为：%s）", modifiedUser.getUserEmail());
            AppUtils.print("3. 修改密码（当前设置为：%s）", modifiedUser.getPassWord());
            AppUtils.print("4. 修改账号状态（当前设置为：%s）", modifiedUser.isActive() ? "正常" : "禁用");
            AppUtils.print("5. 结束");
            AppUtils.printLine();
            AppUtils.print("请选择：");
            String nextInput = scanner.next();
            if (nextInput.equals("q")) {
                return;
            }
            int choice = Math.toIntExact(Long.parseLong(nextInput));
            switch (choice) {
                case 1:
                    AppUtils.print("请输入新用户名：");
                    modifiedUser.setUserName(scanner.next());
                    break;
                case 2:
                    AppUtils.print("请输入新用户邮箱：");
                    modifiedUser.setUserEmail(scanner.next());
                    break;
                case 3:
                    AppUtils.print("请输入新密码：");
                    modifiedUser.setPassWord(scanner.next());
                    AppUtils.print("请再次输入密码：");
                    String userPassword = scanner.next();
                    while (!userPassword.equals(user.getPassWord())) {
                        AppUtils.print("两次输入的密码不一致！请重新输入。");
                        AppUtils.print("请输入用户密码：");
                        modifiedUser.setPassWord(scanner.next());
                        AppUtils.print("请再次输入密码：");
                        userPassword = scanner.next();
                    }
                    break;
                case 4:
                    AppUtils.print("请输入用户邮箱：");
                    modifiedUser.setUserEmail(scanner.next());
                    break;
                case 5:
                    finishEdit = true;
                    break;
                default:
                    AppUtils.print("无效的选择！请重新选择。");
            }
        }
        AppUtils.print("原始用户信息：");
        printUserInline(user);
        AppUtils.print("更新用户信息：");
        printUserInline(modifiedUser);
        AppUtils.print("是否确认修改？(y/n)");
        String choice2 = scanner.next();
        if (choice2.equals("y")) {
            userService.updateUser(modifiedUser);
            AppUtils.print("修改成功！");
        } else if (choice2.equals("n")) {
            AppUtils.print("取消修改！");
        }
    }

    //     修改用户信息（管理员）
    @Override
    public void updateUserWithAdmin() {
        if (currentUser == null) {
            AppUtils.print("请先登录！");
            login();
        }
        if (!currentUser.getUserEmail().equals(ADMIN_EMAIL)) {
            AppUtils.print("您没有权限访问此功能！");
            return;
        }
        while (currentUser != null) {
            ArrayList<UserEntity> users = userService.selectAllUser();
            printAllUser(users);
            AppUtils.print("请输入要修改的用户ID(退出：q ）：");
            String nextInput = scanner.next();
            if (nextInput.equals("q")) {
                return;
            }
            UserEntity targetUser = userService.selectUser(Long.parseLong(nextInput));
            if (targetUser == null) {
                AppUtils.print("用户不存在！");
                continue;
            }
            updateUser(targetUser);
        }

    }

    //     打印用户信息
    @Override
    public void printUser(UserEntity user) {
        AppUtils.printDoubleLine();
        AppUtils.print("用户信息如下：");
        AppUtils.printLine();
        AppUtils.print("用户ID：%s", user.getUserId());
        AppUtils.print("用户邮箱：%s", user.getUserEmail());
        AppUtils.print("用户密码：%s", user.getPassWord());
        AppUtils.print("用户名：%s", user.getUserName());
        AppUtils.print("用户状态：%s", user.isActive() ? "正常" : "禁用");
        AppUtils.printDoubleLine();
    }

    //     打印用户信息（行内）
    @Override
    public void printUserInline(UserEntity user) {
        AppUtils.print(user.toString());
    }

    //     批量打印用户信息
    @Override
    public void printAllUser(ArrayList<UserEntity> users) {
        AppUtils.printDoubleLine();
        AppUtils.print("所有用户信息如下：");
        AppUtils.printLine();
        for (UserEntity user : users) {
            printUserInline(user);
        }
        AppUtils.printDoubleLine();
    }
}