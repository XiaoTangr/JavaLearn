package cn.javat.javaLearn.experiment4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderEntity {
    private long orderId;
    private long userId;
    private long vehicleId;
    private int buyCount;
    private Double totalPrice;
    private Long createTime;
}
