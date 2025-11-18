package cn.javat.javaLearn.experiment4.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 促销活动实体类
 */
public class PromotionEntity {
    private Long id;
    private String name;              // 活动名称
    private String description;       // 活动描述
    private Integer type;             // 活动类型：1-限时折扣，2-满减活动
    private BigDecimal discount;      // 折扣率(如0.9表示9折)或满减金额
    private BigDecimal minPurchase;   // 最低购买金额（用于满减活动）
    private Date startTime;           // 开始时间
    private Date endTime;             // 结束时间
    private Boolean isActive;         // 是否激活
    
    // 构造函数
    public PromotionEntity() {}
    
    public PromotionEntity(Long id, String name, String description, Integer type,
                          BigDecimal discount, BigDecimal minPurchase, 
                          Date startTime, Date endTime, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.discount = discount;
        this.minPurchase = minPurchase;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = isActive;
    }
    
    // Getter和Setter方法
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public BigDecimal getDiscount() {
        return discount;
    }
    
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    
    public BigDecimal getMinPurchase() {
        return minPurchase;
    }
    
    public void setMinPurchase(BigDecimal minPurchase) {
        this.minPurchase = minPurchase;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    /**
     * 检查促销活动是否有效
     * @return boolean
     */
    public boolean isValid() {
        Date now = new Date();
        return isActive && 
               now.after(startTime) && 
               now.before(endTime);
    }
    
    /**
     * 计算折扣后的价格
     * @param originalPrice 原价
     * @return 折扣后价格
     */
    public BigDecimal calculateDiscountedPrice(BigDecimal originalPrice) {
        if (!isValid()) {
            return originalPrice;
        }
        
        if (type == 1) {  // 限时折扣
            return originalPrice.multiply(discount);
        } else if (type == 2) {  // 满减活动
            if (originalPrice.compareTo(minPurchase) >= 0) {
                return originalPrice.subtract(discount);
            }
        }
        return originalPrice;
    }
}