package models.airline;

import java.io.Serializable;

public class Seat implements Serializable {
    private int seatID;
    private boolean availablility;
    private String seatType;

    public Seat(boolean availablility, String seatType) {
        this.availablility = availablility;
        this.seatType = seatType;
    }

    public Seat(int seatID, boolean availablility, String seatType) {
        this.seatID = seatID;
        this.availablility = availablility;
        this.seatType = seatType;
    }

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public boolean isAvailablility() {
        return availablility;
    }

    public void setAvailablility(boolean availablility) {
        this.availablility = availablility;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

}
