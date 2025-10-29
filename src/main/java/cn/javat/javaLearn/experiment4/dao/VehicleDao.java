package cn.javat.javaLearn.experiment4.dao;

import cn.javat.javaLearn.experiment4.entity.Vehicles.CommercialVehicleEntity;
import cn.javat.javaLearn.experiment4.entity.Vehicles.PassengerVehicleEntity;
import cn.javat.javaLearn.experiment4.entity.Vehicles.VehicleEntity;

import java.util.ArrayList;

public interface VehicleDao {
    int insert(VehicleEntity vehicle);

    int update(VehicleEntity vehicle);

    int delete(long pk);

    VehicleEntity select(long pk);

    ArrayList<VehicleEntity> selectAll();
    
    // 特定类型的查询方法
    default ArrayList<PassengerVehicleEntity> selectAllPassengerVehicles() {
        // 默认实现将在实现类中被覆盖
        throw new UnsupportedOperationException("需要在实现类中覆盖此方法");
    }
    
    default ArrayList<CommercialVehicleEntity> selectAllCommercialVehicles() {
        // 默认实现将在实现类中被覆盖
        throw new UnsupportedOperationException("需要在实现类中覆盖此方法");
    }
}