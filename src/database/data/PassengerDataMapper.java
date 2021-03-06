package database.data;

import java.io.Serializable;
import java.rmi.RemoteException;
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
import rmi.PassengerInterface;
import rmi.TicketInterface;

public class PassengerDataMapper implements Serializable {
    private MongoCollection userCollection = DatabaseConnection.getCollection("users");

    private TicketDataMapper ticketDataMapper = new TicketDataMapper();

    public ArrayList<Ticket> getTicketsArrayList(Iterator<Document> cursor) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        while (cursor.hasNext()) {
            Document ticketDoc = cursor.next();

            // ObjectId hostId = hostDoc.getObjectId("_id");
            // String passportNo = hostDoc.getString("passportNumber");
            // String name = hostDoc.getString("name");
            // ArrayList<String> languages = hostDoc.get("languages", new
            // ArrayList<String>().getClass());

            // Ticket ticket = new Ticket(ticketId, price, requestExtraWeight,
            // requestWheelChair, type, ticketstate, airlineTripSeats, payment, passenger,
            // enquiry)
            // tickets.add(ticket);
        }

        return tickets;
    }

    public void updateTickets(TicketInterface ticket, PassengerInterface passenger) throws RemoteException {
        System.out.println("Erroooor");
        userCollection.updateOne(Filters.eq("_id", passenger.getUserId()),
                Updates.push("tickets", ticketDataMapper.createTicketDocument(ticket)));
    }

    public void updateCompanions(Passenger companion, Passenger passenger) {
        userCollection.updateOne(Filters.eq("_id", passenger.getUserId()),
                Updates.push("companions", companion.getUserId()));
    }

    public boolean removeTicket(int ticketId, PassengerInterface passenger) throws RemoteException {
        try {
            Document passengerDoc = (Document) userCollection.find(Filters.eq("_id", passenger.getUserId())).first();
            ArrayList<Document> ticketsDocs = passengerDoc.get("tickets", new ArrayList<Document>().getClass());
            ticketsDocs.remove(ticketId);
            userCollection.updateOne(Filters.eq("_id", passenger.getUserId()),
                    Updates.set("tickets", ticketsDocs));
            return true;
        } catch (UnsupportedOperationException e) {
            System.out.println(e);
            return false;
        }
    }

    public ArrayList<TicketInterface> fetchTickets(Passenger passenger) {
        Document passengerDoc = (Document) userCollection.find(Filters.eq("_id", passenger.getUserId())).first();
        ArrayList<Document> ticketsDocs = passengerDoc.get("tickets", new ArrayList<Document>().getClass());

        ArrayList<TicketInterface> tickets = new ArrayList<TicketInterface>();
        ticketsDocs.forEach((ticketDoc) -> {
            try {
                tickets.add(ticketDataMapper.createTicketObj(ticketDoc));
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        return tickets;
    }

    // public ArrayList<ObjectId> fetchCompanion(Passenger passenger) {
    //     Document passengerDoc = (Document) userCollection.find(Filters.eq("_id", passenger.getUserId())).first();
    //     ArrayList<ObjectId> companions = passengerDoc.get("companions", new ArrayList<ObjectId>().getClass());
    //     return companions;
    // }

    public boolean removeCompanion(ObjectId companionId, Passenger passenger) {
        Document passengerDoc = (Document) userCollection.find(Filters.eq("_id", passenger.getUserId())).first();
        ArrayList<ObjectId> companions = passengerDoc.get("companions", new ArrayList<ObjectId>().getClass());

        for(int i = 0; i < companions.size(); i++) {
            if (companions.get(i).equals(companionId)) {
                companions.remove(i);
                  userCollection.updateOne(Filters.eq("_id", passenger.getUserId()),
                    Updates.set("companions", companions));
                return true;
            }
        }
        return false;
    }

}
