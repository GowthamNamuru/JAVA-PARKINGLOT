package parkinglot.strategies.spotassignment;

import parkinglot.models.*;
import parkinglot.services.ParkingLotService;
import parkinglot.services.ParkingSpotService;
import parkinglot.ticketRepository.ParkingLotRepository;

import java.util.List;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy {

    private ParkingLotService parkingLotService;
    private ParkingSpotService parkingSpotService;

    private ParkingLotRepository parkingSpotRepository;

    public RandomSpotAssignmentStrategy(ParkingLotService parkingLotService,
                                        ParkingSpotService parkingSpotService) {
        this.parkingLotService = parkingLotService;
        this.parkingSpotService = parkingSpotService;
    }

    @Override
    public ParkingSpot assignSpot(VehicleType vehicleType, Gate gate) {
        ParkingLot parkingLot = parkingLotService.getParkingLotForGate(gate);
        List<ParkingSpot> parkingSpots = parkingSpotService.getParkingSpotByLot(parkingLot);

        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.AVAILABLE) &&
                    parkingSpot.getSupportedVehicleType().contains(vehicleType)) {
                return parkingSpot;
            }
        }
        return null;
    }
}
