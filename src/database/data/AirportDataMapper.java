package database.data;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;
import org.bson.types.ObjectId;

import database.DatabaseConnection;
import models.airline.Airport;
import rmi.AirportInterface;

public class AirportDataMapper {
    private MongoCollection airportCollection = DatabaseConnection.getCollection("airports");

    public Document createAirportDocument(Airport airport) {
        Document airportDoc = new Document().append("name", airport.getName()).append("country", airport.getCountry())
                .append("terminalNumbers", airport.getTerminalNumber()).append("hallNumbers", airport.getHallNumber());
        if (airport.getAirportId() != null) {
            airportDoc.append("_id", airport.getAirportId());
        }
        return airportDoc;
    }

    public void insert(Airport airport) {
        Document airportDoc = createAirportDocument(airport);
        airportCollection.insertOne(airportDoc);
    }

    public Airport createAirportObj(Document airportDoc) throws RemoteException {
        ObjectId airportID = airportDoc.getObjectId("_id");
        String name = airportDoc.getString("name");
        String country = airportDoc.getString("country");
        ArrayList<Integer> terminalNumber = airportDoc.get("terminalNumbers", new ArrayList<Integer>().getClass());
        ArrayList<Integer> hallNumber = airportDoc.get("hallNumbers", new ArrayList<Integer>().getClass());

        return new Airport(airportID, name, country, terminalNumber, hallNumber);
    }

    private ArrayList<AirportInterface> getAirportsArrayList(MongoCursor<Document> cursor) {
        ArrayList<AirportInterface> airports = new ArrayList<AirportInterface>();

        while (cursor.hasNext()) {
            Document airportDoc = cursor.next();

            try {
                airports.add(createAirportObj(airportDoc));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return airports;
    }

    public ArrayList<AirportInterface> fetchAirports() {

        MongoCursor<Document> cursor = airportCollection.find().iterator();

        if (cursor == null) {
            return null;
        }

        ArrayList<AirportInterface> airports = getAirportsArrayList(cursor);

        return airports;
    }
}
