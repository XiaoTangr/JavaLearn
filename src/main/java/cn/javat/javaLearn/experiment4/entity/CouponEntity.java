package cn.javat.javaLearn.experiment4.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券实体类
 */
public class CouponEntity {
    private Long id;
    private String code;              // 优惠券代码
    private String name;              // 优惠券名称
    private BigDecimal discount;      // 折扣金额或比例
    private BigDecimal minAmount;     // 最低消费金额
    private Date startTime;           // 开始时间
    private Date endTime;             // 结束时间
    private Boolean isActive;         // 是否激活
    private Integer totalCount;       // 总数量
    private Integer usedCount;        // 已使用数量
    
    // 构造函数
    public CouponEntity() {}
    
    public CouponEntity(Long id, String code, String name, BigDecimal discount, 
                       BigDecimal minAmount, Date startTime, Date endTime, 
                       Boolean isActive, Integer totalCount) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.discount = discount;
        this.minAmount = minAmount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = isActive;
        this.totalCount = totalCount;
        this.usedCount = 0;
    }
    
    // Getter和Setter方法
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public BigDecimal getDiscount() {
        return discount;
    }
    
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    
    public BigDecimal getMinAmount() {
        return minAmount;
    }
    
    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
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
    
    public Integer getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
    public Integer getUsedCount() {
        return usedCount;
    }
    
    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }
    
    /**
     * 检查优惠券是否有效
     * @return boolean
     */
    public boolean isValid() {
        Date now = new Date();
        return isActive && 
               now.after(startTime) && 
               now.before(endTime) && 
               usedCount < totalCount;
    }
    
    /**
     * 使用优惠券
     */
    public void use() {
        if (isValid() && usedCount < totalCount) {
            usedCount++;
        }
    }
}