import parkinglot.ObjectRegistry;
import parkinglot.controllers.TicketController;
import parkinglot.dto.GenerateTicketRequestDto;
import parkinglot.dto.GenerateTicketResponseDto;
import parkinglot.models.ResponseStatus;
import parkinglot.models.Ticket;
import parkinglot.models.VehicleType;
import parkinglot.services.*;
import parkinglot.strategies.spotassignment.RandomSpotAssignmentStrategy;
import parkinglot.strategies.spotassignment.SpotAssignmentStrategy;
import parkinglot.ticketRepository.ParkingLotRepository;
import parkinglot.ticketRepository.TicketRepository;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        VehicleService vehicleService = new VehicleService();
        GateService gateService = new GateService();
        TicketRepository ticketRepository = new TicketRepository();
        ParkingLotRepository parkingSpotRepository = new ParkingLotRepository();
        ParkingLotService parkingLotService = new ParkingLotService(parkingSpotRepository);
        ParkingSpotService parkingSpotService = new ParkingSpotService();
        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy(parkingLotService, parkingSpotService);

        TicketService ticketService = new TicketService(vehicleService, gateService, spotAssignmentStrategy, ticketRepository);

        TicketController ticketController = new TicketController(ticketService);

        ObjectRegistry objectRegistry = new ObjectRegistry();
        objectRegistry.register("vehicleService", vehicleService);
        objectRegistry.register("gateService", gateService);
        objectRegistry.register("ticketRepository", ticketRepository);
        objectRegistry.register("parkingSpotRepository", parkingSpotRepository);
        objectRegistry.register("spotAssignmentStrategy", spotAssignmentStrategy);

        GenerateTicketRequestDto generateTicketRequestDto = new GenerateTicketRequestDto();
        generateTicketRequestDto.setGateId(123L);
        generateTicketRequestDto.setOperatorId(12345);
        generateTicketRequestDto.setVehicleNumber("AP39MQ2290");
        generateTicketRequestDto.setVehicleType(VehicleType.LARGE);


        GenerateTicketResponseDto responseDto = ticketController.generateTicket(generateTicketRequestDto);

        if (responseDto.getResponseStatus().equals(ResponseStatus.SUCCESS)) {
            Ticket ticket = responseDto.getTicket();
        } else {
            //
            System.out.println("Failed");
        }


    }
}

/*
Requirements
1. Operator Should be able to generate the ticket
2. Admin should be able to create a new

MVC -> Model, View, Controller

TicketService depends on -> VehicleService, GateService, SpotAssignmentStrategy, TicketRepository
TicketController depends on -> Ticket Service
Topological sorting
 */