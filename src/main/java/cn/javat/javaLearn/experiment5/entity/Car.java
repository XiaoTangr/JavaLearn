package cn.javat.javaLearn.experiment5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 汽车实体类
 * 用于表示车辆品牌和型号信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private String brand;

    private String model;

    @Override
    public String toString() {
        return "汽车{" + "品牌='" + brand + '\'' + ", 型号='" + model + '\'' + '}';
    }
}