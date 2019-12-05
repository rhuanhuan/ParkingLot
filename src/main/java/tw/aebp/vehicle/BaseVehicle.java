package tw.aebp.vehicle;

import lombok.Getter;

public class BaseVehicle implements Vehicle {
    @Getter
    private String plateNumber;

    public BaseVehicle(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
