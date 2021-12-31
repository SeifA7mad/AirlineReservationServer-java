package database.data;

import com.mongodb.client.MongoCollection;

import org.bson.Document;

import database.DatabaseConnection;
import models.airline.airlineTrip.AirlineTrip;

public class AirlineTripDataMapper {

    private MongoCollection airlineTripCollection = DatabaseConnection.getCollection("airlineTrips");

    public void insert(AirlineTrip airlineTrip) {
        //TODO: add whole object not only id
        Document airlineDoc = new Document().append("maxNumberOfTickets", airlineTrip.getMaxNumberOfTickets()).append(
                "airplaneId",
                airlineTrip.getAirplane())
                .append("orginAirportId", airlineTrip.getOrigin())
                .append("destinationAirportId", airlineTrip.getDestination())
                .append("airlineTripDetatils", airlineTrip.getAirlineTripDetails())
                .append("airlineCost", airlineTrip.getAirlineCost())
                .append("crewId", airlineTrip.getCrew());
        airlineTripCollection.insertOne(airlineDoc);
    }
}
