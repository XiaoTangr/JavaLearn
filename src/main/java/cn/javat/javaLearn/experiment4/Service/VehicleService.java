package cn.javat.javaLearn.experiment4.Service;

import cn.javat.javaLearn.experiment4.entity.Vehicles.VehicleEntity;

import java.util.ArrayList;

public interface VehicleService {
    /**
     * 查询车辆
     *
     * @param carId 车辆ID
     * @return 车辆
     */
    VehicleEntity selectVehicle(int carId);

    /**
     * 获取车辆数量
     *
     * @return 数量
     */
    ArrayList<VehicleEntity> selectAllVehicle();

    /**
     * 插入车辆
     *
     * @param vehicle 车辆
     * @return 插入结果 0: 插入成功 -1: 插入失败
     */
    int insertVehicle(VehicleEntity vehicle);

    /**
     * 更新车辆
     *
     * @param vehicle 车辆
     * @return 0 插入成功 -1 插入失败
     */
    int updateVehicle(VehicleEntity vehicle);

    /**
     * 获取车辆数量
     *
     * @return 数量
     */
    int getDataCount();

    /**
     * 删除车辆
     *
     * @param carId 车辆ID
     * @return 0: 删除成功 -1: 删除失败
     */
    int DeleteVehicle(int carId);
}
