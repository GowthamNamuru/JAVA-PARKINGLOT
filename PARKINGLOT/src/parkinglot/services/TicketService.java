package parkinglot.services;

import parkinglot.exceptions.InvalidRequestInfoException;
import parkinglot.exceptions.NoParkingSlotAvailableException;
import parkinglot.models.*;
import parkinglot.strategies.spotassignment.SpotAssignmentStrategy;
import parkinglot.ticketRepository.TicketRepository;

import java.util.Date;

public class TicketService {

    private final VehicleService vehicleService;

    private final GateService gateService;

    private final SpotAssignmentStrategy spotAssignmentStrategy;

    private TicketRepository ticketRepository;

    public TicketService(VehicleService vehicleService, GateService gateService, SpotAssignmentStrategy spotAssignmentStrategy, TicketRepository ticketRepository) {
        this.vehicleService = vehicleService;
        this.gateService = gateService;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.ticketRepository = ticketRepository;
    }

    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType, Long gateId) throws InvalidRequestInfoException, NoParkingSlotAvailableException {
        /*
        1. Using VehicleNumber fetch the Vehicle details from DB
        vehicleService.getVehicleByNumber
        vehicleRepository.fetchVehicleDetails
        2. Invoke GateService.
         */

        Vehicle vehicle = vehicleService.getVehicle(vehicleNumber);

        if (vehicle == null) {
            vehicleService.registerVehicle(vehicleNumber, vehicleType);
        }

        Gate gate = new Gate();

        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setGate(gate);
        ticket.setEntryTime(new Date());
        ticket.setOperator(gate.getOperator());

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(vehicleType, gate);
        if (parkingSpot == null) {
            throw new NoParkingSlotAvailableException("NoParkingSlotAvailableException");
        }
        ticket.setParkingSpot(parkingSpot);

        Ticket savedTicket = ticketRepository.save(ticket);

        return savedTicket;
    }
}
