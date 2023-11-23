package parkinglot.strategies.spotassignment;

import parkinglot.models.Gate;
import parkinglot.models.ParkingSpot;
import parkinglot.models.VehicleType;
import parkinglot.ticketRepository.ParkingSpotRepository;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy {

    private ParkingSpotRepository parkingSpotRepository;

    public RandomSpotAssignmentStrategy(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Override
    public ParkingSpot assignSpot(VehicleType vehicleType, Gate gate) {
        ParkingSpot parkingSpot = new ParkingSpot();
        return parkingSpot;
    }
}
