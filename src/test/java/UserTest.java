import cn.javat.javaLearn.experiment4.entity.UserEntity;
import cn.javat.javaLearn.experiment4.entity.Vehicles.CommercialVehicleEntity;
import cn.javat.javaLearn.experiment4.Service.Impl.UserServiceImpl;
import cn.javat.javaLearn.experiment4.Service.Impl.VehicleServiceImpl;
import cn.javat.javaLearn.experiment4.Service.UserService;
import cn.javat.javaLearn.experiment4.Service.VehicleService;
import cn.javat.javaLearn.experiment4.Utils.AppUtils;
import org.junit.jupiter.api.Test;

public class UserTest {


    @Test
    public void insetUser() {
        UserService userService = new UserServiceImpl();
        UserEntity test = new UserEntity(1, "test@test.com", "test", "test", true);
        Object res = userService.register(test);
        AppUtils.print(res.toString());
    }


    @Test
    public void loginTest() {
        UserService userService = new UserServiceImpl();
        Object res = userService.login("test@test.com", "test");
        AppUtils.print(res.toString());
    }

    @Test
    public void VehicleInsertTest() {
        VehicleService vehicleService = new VehicleServiceImpl();
        CommercialVehicleEntity vehicle = CommercialVehicleEntity.builder()
                .vehicleId(1)
                .vehicleType("commercial")
                .vehicleBrand("test")
                .vehicleModel("test")
                .vehiclePrice(1000)
                .vehicleStock(10)
                .loadCapacity(100)
                .cargoVolume(100)
                .build();
//        Object res = vehicleService.insertVehicle(vehicle);
        vehicle.setVehicleBrand("tesssssst");
        Object res1 = vehicleService.updateVehicle(vehicle);
        AppUtils.print(res1.toString());

        AppUtils.print(vehicleService.selectVehicle(1).toString());

    }
}
