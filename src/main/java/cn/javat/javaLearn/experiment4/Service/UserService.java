package cn.javat.javaLearn.experiment4.Service;

import cn.javat.javaLearn.experiment4.entity.UserEntity;

import java.util.ArrayList;

public interface UserService {
    /**
     * 注册
     *
     * @param user 用户
     * @return 成功：用户 失败 null
     */
    UserEntity register(UserEntity user);

    /**
     * 登录
     *
     * @param userEmail    用户邮箱
     * @param userPassword 用户密码
     * @return 用户 登录成功  -1 账号密码错误  -2 账号不存在  -3 账号被禁用
     */
    Object login(String userEmail, String userPassword);

    /**
     * 查询用户
     *
     * @param userId 用户ID
     * @return 用户
     */
    UserEntity selectUser(long userId);

    /**
     * 查询所有用户
     *
     * @return 所有用户
     */
    ArrayList<UserEntity> selectAllUser();


}
