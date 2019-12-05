package tw.aebp.parking;

import tw.aebp.passport.BasePassport;
import tw.aebp.passport.Passport;
import lombok.Getter;
import lombok.Setter;
import tw.aebp.vehicle.Vehicle;

import java.util.HashMap;

public class BaseParking implements ParkingLot{

    @Getter
    @Setter
    private int capacity;

    private HashMap<Passport, Vehicle> parkingMap = new HashMap<>();

    public BaseParking(int capacity, HashMap<Passport, Vehicle> parkingMap) {
        this.capacity = capacity;
        this.parkingMap = parkingMap;
    }

    public BaseParking(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public synchronized Passport park(Vehicle vehicle) throws Exception {
        if (this.capacity > 0) {
            this.capacity -= 1;
            BasePassport passport = new BasePassport();
            this.parkingMap.put(passport, vehicle);

            return passport;
        } else {
            throw new Exception("ParkingLot is full");
        }
    }

    @Override
    public synchronized Vehicle pickUp(Passport passport) throws Exception {
        Vehicle vehicle = this.parkingMap.get(passport);
        if (vehicle != null) {
            this.parkingMap.remove(passport);
            return vehicle;
        } else {
            throw new Exception("Passport invalid");
        }
    }

    public int getCapacity() {
        return capacity;
    }

}
