package cn.javat.javaLearn.experiment5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 * 用于表示系统中的用户及其类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;
    private UserType userType;


    @Override
    public String toString() {
        return "用户{" + "姓名='" + name + '\'' + ", 用户类型=" + userType + '}';
    }

    public enum UserType {
        REGULAR,
        VIP,
        SVIP
    }
}