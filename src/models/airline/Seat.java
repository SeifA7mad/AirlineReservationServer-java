package models.airline;

public class Seat {
    private int seatID;
    private boolean availablility;
    private String seatType;

    public Seat(boolean availablility, String seatType) {
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
