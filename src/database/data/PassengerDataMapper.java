package database.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import org.bson.types.ObjectId;

import database.DatabaseConnection;
import models.airline.Seat;
import models.airline.airlineTrip.AirlineTrip;
import models.ticket.Enquiry;
import models.ticket.Payment;
import models.ticket.Ticket;
import models.user.Passenger;

public class PassengerDataMapper {
    private MongoCollection userCollection = DatabaseConnection.getCollection("users");
    private UserDataMapper userDataMapper = new UserDataMapper();

    public Document createSeatDocument(Seat seat) {
        Document seatDoc = new Document().append("seatId", seat.getSeatID())
                .append("availablility", seat.isAvailablility()).append("seatType", seat.getSeatType());
        return seatDoc;
    }

    public Document createEnquiryDocument(Enquiry enquiry) {

        if (enquiry == null) {
            return null;
        }
        Document enquiryDoc = new Document().append("title", enquiry.getTitle()).append("description",
                enquiry.getTitle());
        return enquiryDoc;
    }

    public Document createPaymentDocument(Payment payment) {
        Document paymentDoc = new Document().append("creditcardNumber", payment.getCreditcardNumber())
                .append("nameOnCard", payment.getNameOnCard()).append("expiredDate", payment.getExpiredDate());
        return paymentDoc;
    }

    public ArrayList<Document> createAirlineTripSeats(HashMap<ObjectId, Seat> airlineTripSeats) {
        ArrayList<Document> airlineTripSeatDocuments = new ArrayList<Document>();

        for (Map.Entry<ObjectId, Seat> kv : airlineTripSeats.entrySet()) {
            Document doc = new Document();
            doc.put("airlineTripId", kv.getKey());
            doc.put("seat", createSeatDocument(kv.getValue()));
        }
        return airlineTripSeatDocuments;
    }

    // public ArrayList<Host> getTicketsArrayList(Iterator<Document> cursor) {
    // ArrayList<Host> hosts = new ArrayList<Host>();

    // while (cursor.hasNext()) {
    // Document hostDoc = cursor.next();

    // ObjectId hostId = hostDoc.getObjectId("_id");
    // String passportNo = hostDoc.getString("passportNumber");
    // String name = hostDoc.getString("name");
    // ArrayList<String> languages = hostDoc.get("languages", new
    // ArrayList<String>().getClass());

    // Host host = new Host(hostId, passportNo, name, languages);
    // hosts.add(host);
    // }

    // return hosts;
    // }

    public Document createTicketDocument(Ticket ticket) {
        Document ticketDoc = new Document().append("price", ticket.getPrice())
                .append("requestExtraWeight", ticket.getRequestExtraWeight())
                .append("requestWheelChair", ticket.isRequestWheelChair()).append("type", ticket.getType())
                .append("ticketstate", ticket.getTicketstate().getClass()).append("payment",
                        createPaymentDocument(ticket.getPayment()))
                .append("enquiry", createEnquiryDocument(ticket.getEnquiry()))
                .append("airlineTripSeat", createAirlineTripSeats(ticket.getAirlineTripSeats()));
        return ticketDoc;
    }

    public void updateTickets(Ticket ticket, Passenger passenger) {
        userCollection.updateOne(Filters.eq("_id", passenger.getUserId()),
                Updates.push("tickets", createTicketDocument(ticket)));
    }

    public void updateCompanions(Passenger companion, Passenger passenger) {
        userCollection.updateOne(Filters.eq("_id", passenger.getUserId()),
                Updates.push("companions", companion.getUserId()));
    }
}
