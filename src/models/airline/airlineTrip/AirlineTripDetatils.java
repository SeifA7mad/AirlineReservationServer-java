package models.airline.airlineTrip;

import java.time.LocalDateTime;

public class AirlineTripDetatils {
    private LocalDateTime depatureDateTime;
    private LocalDateTime arrivalDateTime;
    private int destinationTerminalNo;
    private int orginHallNo;

    public AirlineTripDetatils(
            String depatureDateTime, String arrivalDateTime, int destinationTerminalNo, int orginHallNo) {
        this.depatureDateTime = LocalDateTime.parse(depatureDateTime);
        this.arrivalDateTime = LocalDateTime.parse(arrivalDateTime);
        this.destinationTerminalNo = destinationTerminalNo;
        this.orginHallNo = orginHallNo;
    }
    
    public LocalDateTime getDepatureDateTime() {
        return depatureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public int getDestinationTerminalNo() {
        return destinationTerminalNo;
    }

    public int getOrginHallNo() {
        return orginHallNo;
    }
}
