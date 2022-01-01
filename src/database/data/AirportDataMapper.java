package database.data;

import java.util.ArrayList;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;
import org.bson.types.ObjectId;

import database.DatabaseConnection;
import models.airline.Airport;

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

    public Airport createAirportObj(Document airportDoc) {
        ObjectId airportID = airportDoc.getObjectId("_id");
        String name = airportDoc.getString("name");
        String country = airportDoc.getString("country");
        ArrayList<Integer> terminalNumber = airportDoc.get("terminalNumbers", new ArrayList<Integer>().getClass());
        ArrayList<Integer> hallNumber = airportDoc.get("hallNumbers", new ArrayList<Integer>().getClass());

        return new Airport(airportID, name, country, terminalNumber, hallNumber);
    }

    private ArrayList<Airport> getAirportsArrayList(MongoCursor<Document> cursor) {
        ArrayList<Airport> airports = new ArrayList<Airport>();

        while (cursor.hasNext()) {
            Document airportDoc = cursor.next();

            airports.add(createAirportObj(airportDoc));
        }

        return airports;
    }

    public ArrayList<Airport> fetchAirports() {

        MongoCursor<Document> cursor = airportCollection.find().iterator();

        if (cursor == null) {
            return null;
        }

        ArrayList<Airport> airports = getAirportsArrayList(cursor);

        return airports;
    }
}
