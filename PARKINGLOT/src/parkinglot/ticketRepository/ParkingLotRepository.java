package parkinglot.ticketRepository;

import parkinglot.models.Gate;
import parkinglot.models.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {
    private Map<Long, ParkingLot> parkingLotMap = new HashMap<>();

    public ParkingLot getParkingLotByGate(Gate gate) {
        for (ParkingLot parkingLot : parkingLotMap.values()) {
            for (Gate gate1 :  parkingLot.getGates()) {
                return parkingLot;
            }
        }
        return null;
    }

}
