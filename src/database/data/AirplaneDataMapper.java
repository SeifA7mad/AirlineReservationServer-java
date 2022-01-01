package database.data;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import org.bson.types.ObjectId;

import database.DatabaseConnection;

import models.airline.Airplane;
import models.airline.Seat;

public class AirplaneDataMapper {
    private MongoCollection airplaneCollection = DatabaseConnection.getCollection("airplanes");

    public Document createAirplaneDocument(Airplane airplane) {

        ArrayList<Document> seatsDoc = new ArrayList<Document>();
        airplane.getSeats().forEach((seat) -> {
            Document seatDoc = new Document().append("availablility", seat.isAvailablility()).append("seatType",
                    seat.getSeatType().toString());
            seatsDoc.add(seatDoc);
        });

        Document airplaneDoc = new Document().append("name", airplane.getName()).append("type", airplane.getType())
                .append("onDuty", airplane.isOnDuty()).append("level", airplane.getLevel().toString())
                .append("seats",
                        seatsDoc)
                .append("maxSeatsCapacity", airplane.getMaxSeatsCapacity())
                .append("maxWeightPerSeat", airplane.getMaxWeightPerSeat())
                .append("maxWeightCapacity", airplane.getMaxWeightCapacity())
                .append("maxBusnissSeats", airplane.getMaxBusnissSeats())
                .append("maxTravelDistance", airplane.getMaxTravelDistance());

        if (airplane.getAirplaneID() != null) {
            airplaneDoc.append("_id", airplane.getAirplaneID());
        }

        return airplaneDoc;
    }

    public void insert(Airplane airplane) {
        Document airplaneDoc = createAirplaneDocument(airplane);

        airplaneCollection.insertOne(airplaneDoc);
    }

    public Airplane createAirplaneObj(Document airplaneDoc) {

        ObjectId airplaneID = airplaneDoc.getObjectId("_id");
        String name = airplaneDoc.getString("name");
        String type = airplaneDoc.getString("type");
        Boolean onDuty = airplaneDoc.getBoolean("onDuty");
        String level = airplaneDoc.getString("level");

        ArrayList<Document> seatsDoc = airplaneDoc.get("seats", new ArrayList<Document>().getClass());

        ArrayList<Seat> seats = new ArrayList<Seat>();

        seatsDoc.forEach((seatDoc) -> {
            seats.add(new Seat(seatDoc.getBoolean("availablility"), seatDoc.getString("seatType")));
        });

        int maxSeatsCapacity = airplaneDoc.getInteger("maxSeatsCapacity", 0);
        int maxWeightPerSeat = airplaneDoc.getInteger("maxWeightPerSeat", 0);
        int maxBusnissSeats = airplaneDoc.getInteger("maxBusnissSeats", 0);
        int maxWeightCapacity = airplaneDoc.getInteger("maxWeightCapacity", 0);
        double maxTravelDistance = airplaneDoc.getDouble("maxTravelDistance");

        return new Airplane(airplaneID, name, type, onDuty, level, seats, maxSeatsCapacity,
                maxBusnissSeats, maxWeightPerSeat, maxWeightCapacity, maxTravelDistance);
    }

    private ArrayList<Airplane> getAirplanesArrayList(MongoCursor<Document> cursor) {
        ArrayList<Airplane> airplanes = new ArrayList<Airplane>();

        while (cursor.hasNext()) {
            Document airplaneDoc = cursor.next();

            airplanes.add(createAirplaneObj(airplaneDoc));
        }

        return airplanes;
    }

    public ArrayList<Airplane> fetchAirplanes() {

        MongoCursor<Document> cursor = airplaneCollection.find().iterator();

        if (cursor == null) {
            return null;
        }

        ArrayList<Airplane> airplanes = getAirplanesArrayList(cursor);

        return airplanes;
    }

    public void updateAirplane(ObjectId airplaneId, boolean onDuty) {
        airplaneCollection.updateOne(Filters.eq("_id", airplaneId), Updates.set("onDuty", onDuty));
    }

}
