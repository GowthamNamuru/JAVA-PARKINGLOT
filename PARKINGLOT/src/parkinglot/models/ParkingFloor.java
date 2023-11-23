package parkinglot.models;

import java.util.List;

public class ParkingFloor extends BaseEntity {
    private int number;
    private List<ParkingSpot> slots;
    private ParkingFloorStatus parkingFloorStatus;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<ParkingSpot> getSlots() {
        return slots;
    }

    public void setSlots(List<ParkingSpot> slots) {
        this.slots = slots;
    }

    public ParkingFloorStatus getParkingFloorStatus() {
        return parkingFloorStatus;
    }

    public void setParkingFloorStatus(ParkingFloorStatus parkingFloorStatus) {
        this.parkingFloorStatus = parkingFloorStatus;
    }
}
