package tw.aebp.parking;

import tw.aebp.passport.Passport;
import tw.aebp.vehicle.Vehicle;

public interface ParkingLot {
    Passport park(Vehicle vehicle) throws Exception;

    Vehicle pickUp(Passport passport) throws Exception;
}
