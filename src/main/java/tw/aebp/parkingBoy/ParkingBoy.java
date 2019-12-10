package tw.aebp.parkingBoy;

import com.oracle.tools.packager.Log;
import tw.aebp.parking.BaseParking;
import tw.aebp.passport.Passport;
import tw.aebp.vehicle.Vehicle;

import java.util.List;

public class ParkingBoy {

    private List<? extends BaseParking> parkingLots;

    public ParkingBoy(List<? extends BaseParking> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public synchronized Passport park(Vehicle vehicle) throws Exception {
        int size = parkingLots.size();
        int currentParkingLot = 0;

        while (currentParkingLot < size) {
            if (parkingLots.get(currentParkingLot).getCapacity() > 0) {
                return parkingLots.get(currentParkingLot).park(vehicle);
            } else {
                currentParkingLot += 1;
            }
        }

        throw new Exception("ParkingLot is full");
    }

    public synchronized Vehicle pick(Passport passport) throws Exception {
        int size = parkingLots.size();
        int currentParkingLot = 0;

        while (currentParkingLot < size) {
            try {
                Vehicle vehicle = parkingLots.get(currentParkingLot).pickUp(passport);
                return vehicle;
            } catch (Exception e) {
                Log.debug("Find next parking lot");
                currentParkingLot += 1;
            }
        }

        throw new Exception("Passport invalid");
    }
}
