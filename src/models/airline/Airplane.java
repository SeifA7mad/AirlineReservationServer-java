package models.airline;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import database.data.AirplaneDataMapper;

public class Airplane {
    private ObjectId airplaneID;
    private String name;
    private String type;
    private boolean onDuty;
    private String level;
    private ArrayList<Seat> seats;
    private int maxSeatsCapacity;
    private int maxBusnissSeats;
    private int maxWeightPerSeat;
    private int maxWeightCapacity;
    private double maxTravelDistance;

    private AirplaneDataMapper mapper = new AirplaneDataMapper();

    public Airplane() {

    }

    public Airplane(ObjectId airplaneID, String name, String type, boolean onDuty, String level, ArrayList<Seat> seats,
            int maxSeatsCapacity,
            int maxBusnissSeats,
            int maxWeightPerSeat, int maxWeightCapacity, double maxTravelDistance) {
        this.airplaneID = airplaneID;
        this.name = name;
        this.type = type;
        this.onDuty = onDuty;
        this.level = level;
        this.maxSeatsCapacity = maxSeatsCapacity;
        this.maxBusnissSeats = maxBusnissSeats;
        this.maxWeightPerSeat = maxWeightPerSeat;
        this.maxWeightCapacity = maxWeightCapacity;
        this.maxTravelDistance = maxTravelDistance;
        this.seats = seats;
    }

    public void addAirplane(String name, String type, String level, int maxSeatsCapacity, int maxBusnissSeats,
            int maxWeightPerSeat, double maxTravelDistance) {
        this.name = name;
        this.type = type;
        this.onDuty = false;
        this.level = level;
        this.maxSeatsCapacity = maxSeatsCapacity;
        this.maxBusnissSeats = maxBusnissSeats;
        this.maxWeightPerSeat = maxWeightPerSeat;
        this.maxWeightCapacity = maxWeightPerSeat * maxSeatsCapacity;
        this.maxTravelDistance = maxTravelDistance;

        this.seats = new ArrayList<Seat>();
        for (int i = 0; i < this.maxSeatsCapacity; i++) {
            if (i < maxBusnissSeats) {
                seats.add(new Seat(true, "BusnissClass"));
                continue;
            }
            seats.add(new Seat(true, "Economy"));
        }

        mapper.insert(this);
    }

    public ArrayList<Airplane> getAirplanes() {
        ArrayList<Airplane> airplanes = null;
        airplanes = mapper.fetchAirplanes();
        if (airplanes == null) {
            return null;
        }
        return airplanes;
    }

    public void updateAirplane(boolean onDuty) {
        this.onDuty = onDuty;
        mapper.updateAirplane(this.airplaneID, this.onDuty);
    }

    public boolean removeHost() {
        return this.mapper.removeAirplane(this);
    }

    // public Airplane getAirplanes(ObjectId airplaneId) {
    //     Airplane airplane = null;
    //     airplane = mapper.fetchAirplanes(airplaneId);
    //     if (airplane == null) {
    //         return null;
    //     }
    //     return airplane;
    // }


    public ObjectId getAirplaneID() {
        return airplaneID;
    }

    public void setAirplaneID(ObjectId airplaneID) {
        this.airplaneID = airplaneID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOnDuty() {
        return onDuty;
    }

    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }

    public String getLevel() {
        return this.level;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public int getMaxSeatsCapacity() {
        return maxSeatsCapacity;
    }

    public void setMaxSeatsCapacity(int maxSeatsCapacity) {
        this.maxSeatsCapacity = maxSeatsCapacity;
    }

    public int getMaxWeightPerSeat() {
        return maxWeightPerSeat;
    }

    public void setMaxWeightPerSeat(int maxWeightPerSeat) {
        this.maxWeightPerSeat = maxWeightPerSeat;
    }

    public int getMaxWeightCapacity() {
        return maxWeightCapacity;
    }

    public int getMaxBusnissSeats() {
        return maxBusnissSeats;
    }

    public void setMaxWeightCapacity(int maxWeightCapacity) {
        this.maxWeightCapacity = maxWeightCapacity;
    }

    public double getMaxTravelDistance() {
        return maxTravelDistance;
    }

    public void setMaxTravelDistance(double maxTravelDistance) {
        this.maxTravelDistance = maxTravelDistance;
    }
}
