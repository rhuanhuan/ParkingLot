package tw.aebp.parking;

import org.junit.Test;
import tw.aebp.passport.BasePassport;
import tw.aebp.passport.Passport;
import tw.aebp.vehicle.BaseVehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BaseParkingTest {
    @Test
    public void should_park_a_vehicle_success_when_have_capacity_left() {
        BaseParking parking = new BaseParking(2);
        BaseVehicle vehicle = new BaseVehicle("京A88888");

        try {
            parking.park(vehicle);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void should_reduce_capacity_by_1_when_park_a_car_and_have_capacity_left() throws Exception {
        BaseParking parking = new BaseParking(2);
        BaseVehicle vehicle = new BaseVehicle("京A88888");
        parking.park(vehicle);

        assertEquals(1, parking.getCapacity());
    }

    @Test
    public void should_park_fail_and_return_null_when_have_no_capacity_left() throws Exception {
        BaseParking parking = new BaseParking(1);
        BaseVehicle vehicle1 = new BaseVehicle("京A88888");
        BaseVehicle vehicle2 = new BaseVehicle("京A88886");
        parking.park(vehicle1);

        try {
            parking.park(vehicle2);
            fail("Should thrown any exception");
        } catch (Exception e) {
            assertEquals("ParkingLot is full", e.getMessage());
        }
    }

    @Test
    public void should_return_the_expected_car_when_pick_up_a_car() throws Exception {
        BaseParking parking = new BaseParking(10);
        BaseVehicle vehicle1 = new BaseVehicle("京A88881");
        BaseVehicle vehicle2 = new BaseVehicle("京A88882");
        BaseVehicle vehicle3 = new BaseVehicle("京A88883");
        Passport passport1 = parking.park(vehicle1);
        Passport passport2 = parking.park(vehicle2);
        Passport passport3 = parking.park(vehicle3);

        assertEquals(vehicle1, parking.pickUp(passport1));
        assertEquals(vehicle2, parking.pickUp(passport2));
        assertEquals(vehicle3, parking.pickUp(passport3));
    }

    @Test
    public void should_pick_up_a_car_failed_when_passport_is_invalid() throws Exception {
        BaseParking parking = new BaseParking(10);
        Passport passport2 = new BasePassport();

        try {
            parking.pickUp(passport2);
            fail("Should thrown any exception");
        } catch (Exception e) {
            assertEquals("Passport invalid", e.getMessage());
        }
    }

    @Test
    public void should_pick_up_a_car_failed_when_passport_is_used() throws Exception {
        BaseParking parking = new BaseParking(10);
        BaseVehicle vehicle = new BaseVehicle("京A88882");

        Passport passport = parking.park(vehicle);
        parking.pickUp(passport);

        try {
            parking.pickUp(passport);
            fail("Should thrown any exception");
        } catch (Exception e) {
            assertEquals("Passport invalid", e.getMessage());
        }
    }
}