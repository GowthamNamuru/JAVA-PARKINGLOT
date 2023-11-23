package parkinglot.services;

import parkinglot.models.Gate;
import parkinglot.models.ParkingLot;
import parkinglot.ticketRepository.ParkingLotRepository;

public class ParkingLotService {
    private ParkingLotRepository parkingLotRepository;


    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLot getParkingLotForGate(Gate gate) {
        return parkingLotRepository.getParkingLotByGate(gate);
    }

}
