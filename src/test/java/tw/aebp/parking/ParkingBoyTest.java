package tw.aebp.parking;

import org.junit.Assert;
import org.junit.Test;
import tw.aebp.parkingBoy.ParkingBoy;
import tw.aebp.passport.Passport;
import tw.aebp.vehicle.BaseVehicle;
import tw.aebp.vehicle.Vehicle;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ParkingBoyTest {
    @Test
    public void should_park_a_vehicle_success_when_have_capacity_left() throws Exception {
        BaseParking parking = new BaseParking(10);
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parking));

        BaseVehicle vehicle = new BaseVehicle("京A88888");
        Passport passport = parkingBoy.park(vehicle);
        Assert.assertNotNull(passport);
    }

    @Test
    public void should_pick_a_vehicle_success_when_have_an_valid_passport() throws Exception {
        BaseVehicle vehicle = new BaseVehicle("京A88888");
        BaseParking parking = new BaseParking(10);
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parking));

        Passport passport = parkingBoy.park(vehicle);
        Vehicle pickedVehicle = parkingBoy.pick(passport);

        Assert.assertEquals(vehicle, pickedVehicle);
    }

    @Test
    public void should_success_when_2nd_parking_lot_have_capacity() throws Exception {
        BaseParking parking1 = new BaseParking(0);
        BaseParking parking2 = new BaseParking(1);
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parking1, parking2));

        BaseVehicle vehicle = new BaseVehicle("京A88888");
        Passport passport = parkingBoy.park(vehicle);
        Assert.assertNotNull(passport);
    }

    @Test
    public void should_park_success_when_parking_lot_have_capacity() throws Exception {
        BaseParking parking1 = new BaseParking(1);
        BaseParking parking2 = new BaseParking(1);
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parking1, parking2));

        BaseVehicle vehicle1 = new BaseVehicle("京A88888");
        BaseVehicle vehicle2 = new BaseVehicle("京A88886");
        Passport passport1 = parkingBoy.park(vehicle1);
        Passport passport2 = parkingBoy.park(vehicle2);

        Assert.assertNotNull(passport1);
        Assert.assertNotNull(passport2);
    }

    @Test
    public void should_park_failed_when_have_no_capacity() {
        BaseParking parking1 = new BaseParking(0);
        BaseParking parking2 = new BaseParking(0);
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parking1, parking2));

        BaseVehicle vehicle = new BaseVehicle("京A88888");
        try {
            parkingBoy.park(vehicle);
            fail("Should thrown any exception");
        } catch (Exception e) {
            assertEquals("ParkingLot is full", e.getMessage());
        }
    }

    @Test
    public void should_pick_the_right_car_when_have_right_passport() throws Exception {
        BaseParking parking1 = new BaseParking(0);
        BaseParking parking2 = new BaseParking(1);
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parking1, parking2));

        BaseVehicle vehicle = new BaseVehicle("京A88888");
        Passport passport = parkingBoy.park(vehicle);
        Assert.assertNotNull(passport);

        Vehicle picked = parkingBoy.pick(passport);
        assertEquals(vehicle, picked);
    }

    @Test
    public void should_pickup_failed_when_have_used_passport() throws Exception {
        BaseParking parking1 = new BaseParking(0);
        BaseParking parking2 = new BaseParking(1);
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parking1, parking2));

        BaseVehicle vehicle = new BaseVehicle("京A88888");
        Passport passport = parkingBoy.park(vehicle);
        Assert.assertNotNull(passport);

        assertEquals(vehicle, parkingBoy.pick(passport));

        try {
            parkingBoy.pick(passport);
            fail("Should thrown any exception");
        } catch (Exception e) {
            assertEquals("Passport invalid", e.getMessage());
        }
    }
}