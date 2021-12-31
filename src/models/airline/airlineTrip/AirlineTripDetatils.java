package models.airline.airlineTrip;

import java.time.LocalDate;

public class AirlineTripDetatils {
    private LocalDate depatureDateTime;
    private LocalDate arrivalDateTime;
    private int destinationTerminalNo;
    private int orginHallNo;
    
    public LocalDate getDepatureDateTime() {
        return depatureDateTime;
    }

    public LocalDate getArrivalDateTime() {
        return arrivalDateTime;
    }

    public int getDestinationTerminalNo() {
        return destinationTerminalNo;
    }

    public int getOrginHallNo() {
        return orginHallNo;
    }
}
