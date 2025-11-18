package cn.javat.javaLearn.experiment4.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 活动统计实体类
 */
public class PromotionStatisticsEntity {
    private Long id;
    private Long promotionId;         // 关联的促销活动ID
    private Long couponId;            // 关联的优惠券ID（可为空）
    private Integer participationCount; // 参与人数
    private Integer orderCount;       // 订单数量
    private BigDecimal totalDiscount; // 总折扣金额
    private BigDecimal totalSales;    // 活动期间总销售额
    private Date statisticsDate;      // 统计日期
    
    // 构造函数
    public PromotionStatisticsEntity() {}
    
    public PromotionStatisticsEntity(Long id, Long promotionId, Long couponId,
                                   Integer participationCount, Integer orderCount,
                                   BigDecimal totalDiscount, BigDecimal totalSales,
                                   Date statisticsDate) {
        this.id = id;
        this.promotionId = promotionId;
        this.couponId = couponId;
        this.participationCount = participationCount;
        this.orderCount = orderCount;
        this.totalDiscount = totalDiscount;
        this.totalSales = totalSales;
        this.statisticsDate = statisticsDate;
    }
    
    // Getter和Setter方法
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getPromotionId() {
        return promotionId;
    }
    
    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }
    
    public Long getCouponId() {
        return couponId;
    }
    
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }
    
    public Integer getParticipationCount() {
        return participationCount;
    }
    
    public void setParticipationCount(Integer participationCount) {
        this.participationCount = participationCount;
    }
    
    public Integer getOrderCount() {
        return orderCount;
    }
    
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }
    
    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }
    
    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
    
    public BigDecimal getTotalSales() {
        return totalSales;
    }
    
    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }
    
    public Date getStatisticsDate() {
        return statisticsDate;
    }
    
    public void setStatisticsDate(Date statisticsDate) {
        this.statisticsDate = statisticsDate;
    }
    
    /**
     * 计算平均订单价值
     * @return 平均订单价值
     */
    public BigDecimal getAverageOrderValue() {
        if (orderCount == null || orderCount == 0) {
            return BigDecimal.ZERO;
        }
        return totalSales.divide(new BigDecimal(orderCount), 2, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 计算ROI (投资回报率)
     * @param marketingCost 营销成本
     * @return ROI
     */
    public BigDecimal calculateROI(BigDecimal marketingCost) {
        if (marketingCost == null || marketingCost.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        // (总销售额 - 总折扣 - 营销成本) / 营销成本
        BigDecimal profit = totalSales.subtract(totalDiscount).subtract(marketingCost);
        return profit.divide(marketingCost, 4, BigDecimal.ROUND_HALF_UP);
    }
}