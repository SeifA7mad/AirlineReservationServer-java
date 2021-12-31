package database.data;

import com.mongodb.client.MongoCollection;

import org.bson.Document;

import database.DatabaseConnection;
import models.airline.Airport;

public class AirportDataMapper {
    private MongoCollection airportCollection = DatabaseConnection.getCollection("airports");

    public Document createAirportDocument(Airport airport) {
        Document airportDoc = new Document().append("name", airport.getName()).append("country", airport.getCountry())
                .append("terminalNumbers", airport.getTerminalNumber()).append("hallNumbers", airport.getHallNumber());
        return airportDoc;
    }

    public void insert(Airport airport) {
        Document airportDoc = createAirportDocument(airport);
        airportCollection.insertOne(airportDoc);
    }
}
