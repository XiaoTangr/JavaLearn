package cn.javat.javaLearn.experiment4.Service.Impl;


import cn.javat.javaLearn.experiment4.dao.Impl.VehicleDaoImpl;
import cn.javat.javaLearn.experiment4.entity.Vehicles.VehicleEntity;
import cn.javat.javaLearn.experiment4.Service.VehicleService;

import java.util.ArrayList;

public class VehicleServiceImpl implements VehicleService {
    
    private final VehicleDaoImpl vehicleDao = new VehicleDaoImpl();

    /**
     * 查询车辆
     *
     * @param carId 车辆ID
     * @return 车辆
     */
    @Override
    public VehicleEntity selectVehicle(int carId) {
        return vehicleDao.select(carId);
    }

    /**
     * 获取车辆数量
     *
     * @return 数量
     */
    @Override
    public ArrayList<VehicleEntity> selectAllVehicle() {
        return vehicleDao.selectAll();
    }

    /**
     * 插入车辆
     *
     * @param vehicle 车辆
     * @return 插入结果 0: 插入成功 -1: 插入失败
     */
    @Override
    public int insertVehicle(VehicleEntity vehicle) {
        int result = vehicleDao.insert(vehicle);
        return result > 0 ? 0 : -1;
    }

    /**
     * 更新车辆
     *
     * @param vehicle 车辆
     * @return 0 插入成功 -1 插入失败
     */
    @Override
    public int updateVehicle(VehicleEntity vehicle) {
        int result = vehicleDao.update(vehicle);
        return result > 0 ? 0 : -1;
    }

    /**
     * 获取车辆数量
     *
     * @return 数量
     */
    @Override
    public int getDataCount() {
        ArrayList<VehicleEntity> vehicles = vehicleDao.selectAll();
        return vehicles != null ? vehicles.size() : 0;
    }

    /**
     * 删除车辆
     *
     * @param carId 车辆ID
     * @return 0: 删除成功 -1: 删除失败
     */
    @Override
    public int DeleteVehicle(int carId) {
        int result = vehicleDao.delete(carId);
        return result > 0 ? 0 : -1;
    }
}