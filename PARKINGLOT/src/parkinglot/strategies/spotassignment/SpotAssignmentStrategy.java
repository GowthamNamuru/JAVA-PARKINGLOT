package parkinglot.strategies.spotassignment;

import parkinglot.models.Gate;
import parkinglot.models.ParkingSpot;
import parkinglot.models.VehicleType;

public interface SpotAssignmentStrategy {
    ParkingSpot assignSpot(VehicleType vehicleType, Gate gate);
}
