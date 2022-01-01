package database.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import org.bson.types.ObjectId;

import database.DatabaseConnection;
import models.airline.Seat;

import models.ticket.Enquiry;
import models.ticket.Payment;
import models.ticket.Ticket;
import models.user.Passenger;

public class PassengerDataMapper {
    private MongoCollection userCollection = DatabaseConnection.getCollection("users");

    private TicketDataMapper ticketDataMapper = new TicketDataMapper();

    public ArrayList<Ticket> getTicketsArrayList(Iterator<Document> cursor) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        while (cursor.hasNext()) {
            Document ticketDoc = cursor.next();

            // ObjectId hostId = hostDoc.getObjectId("_id");
            // String passportNo = hostDoc.getString("passportNumber");
            // String name = hostDoc.getString("name");
            // ArrayList<String> languages = hostDoc.get("languages", new ArrayList<String>().getClass());

            // Ticket ticket = new Ticket(ticketId, price, requestExtraWeight, requestWheelChair, type, ticketstate, airlineTripSeats, payment, passenger, enquiry)
            // tickets.add(ticket);
        }

        return tickets;
    }

    public void updateTickets(Ticket ticket, Passenger passenger) {
        userCollection.findOneAndUpdate(Filters.eq("_id", passenger.getUserId()),
                Updates.push("tickets", ticketDataMapper.createTicketDocument(ticket)));
    }

    public void updateCompanions(Passenger companion, Passenger passenger) {
        userCollection.updateOne(Filters.eq("_id", passenger.getUserId()),
                Updates.push("companions", companion.getUserId()));
    }

    public ArrayList<Ticket> fetchTickets(Passenger passenger) {
        Document passengerDoc = (Document) userCollection.find(Filters.eq("_id", passenger.getUserId())).first();
        ArrayList<Document> ticketsDocs = passengerDoc.get("tickets", new ArrayList<Document>().getClass());

        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        return null;
    }

}
