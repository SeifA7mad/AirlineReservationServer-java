package database.data;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

import org.bson.Document;
import org.bson.types.ObjectId;

import database.DatabaseConnection;
import models.airline.*;
import models.airline.airlineTrip.*;
import models.airline.airlineTrip.airlineTripState.AirlineTripState;
import models.airline.airlineTrip.airlineTripState.EndedState;
import models.airline.airlineTrip.airlineTripState.PublishedState;
import models.airline.airlineTrip.airlineTripState.UnpublishedState;
import models.ticket.Ticket;
import models.user.Passenger;
import models.user.airlineTripObserver.AirlineTripObserver;

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

        ArrayList<Document> airlineDetails = new ArrayList<Document>();
        airlineDetails.add(airlineTripDetailsDoc);

        Document airlineDoc = new Document().append("maxNumberOfTickets", airlineTrip.getMaxNumberOfTickets()).append(
                "airplane",
                airplaneDoc)
                .append("orginAirport",
                        originAirportDoc)
                .append("destinationAirport", destinationAirportDoc)
                .append("airlineTripDetatils",
                        airlineDetails)
                .append("airlineCost", airlineTrip.getAirlineCost())
                .append("crew", crewDoc).append("tickets", tickets)
                .append("airlineTripStatus", airlineTrip.getAirlineTripState().getClass().getSimpleName())
                .append("passengersObservers", new ArrayList<ObjectId>());

        return airlineDoc;
    }

    public void insert(AirlineTrip airlineTrip) {
        Document airlineDoc = createAirlineTripDocument(airlineTrip);
        airlineTripCollection.insertOne(airlineDoc);
    }

    public AirlineTripDetatils createAirlineTripDetatilsObj(Document airlineTripDetailsDoc) {
        String depatureDateTime = airlineTripDetailsDoc.getString("depatureDateTime");
        String arrivalDateTime = airlineTripDetailsDoc.getString("arrivalDateTime");
        int destinationTerminalNo = airlineTripDetailsDoc.getInteger("destinationTerminalNo", 0);
        int orginHallNo = airlineTripDetailsDoc.getInteger("orginHallNo", 0);

        return new AirlineTripDetatils(depatureDateTime, arrivalDateTime, destinationTerminalNo, orginHallNo);
    }

    public AirlineTrip createAirlineTripObj(Document airlineTripDoc) throws RemoteException {
        ObjectId airlineTripID = airlineTripDoc.getObjectId("_id");
        int maxNumberOfTickets = airlineTripDoc.getInteger("maxNumberOfTickets", 0);
        Airplane airplane = airplaneDataMapper.createAirplaneObj((Document) airlineTripDoc.get("airplane"));
        Airport origin = airportDataMapper.createAirportObj((Document) airlineTripDoc.get("orginAirport"));
        Airport destination = airportDataMapper.createAirportObj((Document) airlineTripDoc.get("destinationAirport"));
        ArrayList<Document> airlineTripDetailsDocs = airlineTripDoc.get("airlineTripDetatils",
                new ArrayList<Document>().getClass());

        ArrayList<AirlineTripDetatils> airlineTripDetails = new ArrayList<AirlineTripDetatils>();

        airlineTripDetailsDocs.forEach((airlineTripDetailsDoc) -> {
            airlineTripDetails.add(createAirlineTripDetatilsObj(airlineTripDetailsDoc));
        });
        double airlineCost = airlineTripDoc.getDouble("airlineCost");
        Crew crew = (Crew) crewDataMapper.createCrewObj((Document) airlineTripDoc.get("crew"));
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        AirlineTripState airlineTripState;
        String state = airlineTripDoc.getString("airlineTripStatus");

        if (state.equals("PublishedState")) {
            airlineTripState = new PublishedState();
        } else if (state.equals("UnpublishedState")) {
            airlineTripState = new UnpublishedState();
        } else {
            airlineTripState = new EndedState();
        }
        return new AirlineTrip(airlineTripID, maxNumberOfTickets, airplane, origin, destination, airlineTripDetails,
                airlineCost, tickets, crew, airlineTripState);
    }

    private ArrayList<AirlineTrip> getAirlineArrayList(MongoCursor<Document> cursor) throws RemoteException {
        ArrayList<AirlineTrip> airlineTrips = new ArrayList<AirlineTrip>();

        while (cursor.hasNext()) {
            Document airlineTripDoc = cursor.next();

            airlineTrips.add(createAirlineTripObj(airlineTripDoc));
        }

        return airlineTrips;
    }

    public ArrayList<AirlineTrip> fetchAirlineTripsBy(String from, String to) throws RemoteException {

        MongoCursor<Document> cursor = airlineTripCollection
                .find(Filters.and(Filters.eq("orginAirport.name", from), Filters.eq("destinationAirport.name", to),
                        Filters.eq("airlineTripStatus", "PublishedState")))
                .iterator();

        if (cursor == null) {
            return null;
        }

        ArrayList<AirlineTrip> airlineTrips = getAirlineArrayList(cursor);

        return airlineTrips;
    }

    public AirlineTrip fetchAirlineTripBy(ObjectId airlineTripId) throws RemoteException {
        Document airlineTripDoc = (Document) airlineTripCollection.find(Filters.eq("_id", airlineTripId)).first();

        if (airlineTripDoc == null) {
            System.out.println("NO AIRLINE FOUNDED");
            return null;
        }
        return createAirlineTripObj(airlineTripDoc);
    }

    public boolean removeAirlineTrip(ObjectId airlineTripId) {
        try {
            airlineTripCollection.deleteOne(Filters.eq("_id", airlineTripId));
            return true;
        } catch (UnsupportedOperationException e) {
            System.out.println(e);
            return false;
        }
    }

    public void updatePassengerObservers(AirlineTrip airlineTrip, AirlineTripObserver passenger) {
        airlineTripCollection.findOneAndUpdate(Filters.eq("_id", airlineTrip.getAirlineTripID()),
                Updates.push("passengersObservers", ((Passenger) passenger).getUserId()));
    }
}
