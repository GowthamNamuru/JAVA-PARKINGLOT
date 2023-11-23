package parkinglot.controllers;

import parkinglot.dto.GenerateTicketRequestDto;
import parkinglot.dto.GenerateTicketResponseDto;
import parkinglot.exceptions.InvalidRequestInfoException;
import parkinglot.exceptions.NoParkingSlotAvailableException;
import parkinglot.models.ResponseStatus;
import parkinglot.models.Ticket;
import parkinglot.services.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto generateTicketRequestDto)  {
        // VehicleService -> fetch vehicle details
        // GateService -> fetch gate details
        // TicketService -> pass the vehicle & gate details, create ticket object

        // TicketService -
            // VehicleService -> getVehicle()
            // GateService -> getGateDetails()
            // createTicket
        GenerateTicketResponseDto responseDto = new GenerateTicketResponseDto();
        try {
            Ticket ticket = ticketService.generateTicket(generateTicketRequestDto.getVehicleNumber(),
                    generateTicketRequestDto.getVehicleType(), generateTicketRequestDto.getGateId());
            responseDto.setTicket(ticket);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InvalidRequestInfoException | NoParkingSlotAvailableException e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}

/*
    Problems with directly including the Models inside the method.
    1. Models are getting exposed to the external clients, that is not desirable.
    2. If new param is getting added in the method, then the current client will start failing.
    3. Client will have to make lot of GET requests to get the details of vehicle,


    DTO -> Data Access Objects -> Kind of Model classes, used for request

 */

