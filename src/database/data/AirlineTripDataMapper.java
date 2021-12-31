package database.data;

import java.util.ArrayList;

import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

import database.DatabaseConnection;
import models.airline.airlineTrip.*;

public class AirlineTripDataMapper {

    private MongoCollection airlineTripCollection = DatabaseConnection.getCollection("airlineTrips");
    private AirplaneDataMapper airplaneDataMapper = new AirplaneDataMapper();
    private AirportDataMapper airportDataMapper = new AirportDataMapper();
    private CrewDataMapper crewDataMapper = new CrewDataMapper();

    public Document createAirlineTripDetailsDocument(AirlineTripDetatils airlineTripDetails) {
        Document airlineTripDetailsDoc = new Document()
                .append("depatureDateTime", airlineTripDetails.getDepatureDateTime().toString())
                .append("arrivalDateTime", airlineTripDetails.getArrivalDateTime().toString())
                .append("destinationTerminalNo", airlineTripDetails.getDestinationTerminalNo())
                .append("orginHallNo", airlineTripDetails.getOrginHallNo());

        return airlineTripDetailsDoc;
    }

    public Document createAirlineTripDocument(AirlineTrip airlineTrip) {

        Document airplaneDoc = airplaneDataMapper.createAirplaneDocument(airlineTrip.getAirplane());
        Document originAirportDoc = airportDataMapper.createAirportDocument(airlineTrip.getOrigin());
        Document destinationAirportDoc = airportDataMapper.createAirportDocument(airlineTrip.getDestination());
        Document airlineTripDetailsDoc = createAirlineTripDetailsDocument(airlineTrip.getAirlineTripDetails().get(0));
        Document crewDoc = crewDataMapper.createCrewDocument(airlineTrip.getCrew());
        ArrayList<ObjectId> tickets = new ArrayList<ObjectId>();

        Document airlineDoc = new Document().append("maxNumberOfTickets", airlineTrip.getMaxNumberOfTickets()).append(
                "airplane",
                airplaneDoc)
                .append("orginAirport",
                        originAirportDoc)
                .append("destinationAirport", destinationAirportDoc)
                .append("airlineTripDetatils",
                        airlineTripDetailsDoc)
                .append("airlineCost", airlineTrip.getAirlineCost())
                .append("crew", crewDoc).append("tickets", tickets)
                .append("airlineTripStatus", airlineTrip.getAirlineTripState().getClass().toString());
        return airlineDoc;
    }

    public void insert(AirlineTrip airlineTrip) {
        Document airlineDoc = createAirlineTripDocument(airlineTrip);
        airlineTripCollection.insertOne(airlineDoc);
    }
}
