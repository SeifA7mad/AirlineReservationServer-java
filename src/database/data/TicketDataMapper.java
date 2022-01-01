package database.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;

import models.airline.Seat;
import models.ticket.Enquiry;
import models.ticket.Payment;
import models.ticket.Ticket;
import models.ticket.ticketState.BookingState;
import models.ticket.ticketState.EndBookingState;
import models.ticket.ticketState.TicketState;

public class TicketDataMapper {
    public Document createSeatDocument(Seat seat) {
        Document seatDoc = new Document().append("seatId", seat.getSeatID())
                .append("availablility", seat.isAvailablility()).append("seatType", seat.getSeatType());
        return seatDoc;
    }

    public Seat createSeatObj(Document seatDoc) {

        return new Seat(seatID, availablility, seatType);
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
                .append("nameOnCard", payment.getNameOnCard()).append("expiredDate", payment.getExpiredDate().toString());
        return paymentDoc;
    }

    public ArrayList<Document> createAirlineTripSeats(HashMap<ObjectId, Seat> airlineTripSeats) {
        ArrayList<Document> airlineTripSeatDocuments = new ArrayList<Document>();
        for (Map.Entry<ObjectId, Seat> kv : airlineTripSeats.entrySet()) {
            Document doc = new Document();
            doc.append("airlineTripId", kv.getKey());
            doc.append("seat", createSeatDocument(kv.getValue()));
            airlineTripSeatDocuments.add(doc);
        }
        return airlineTripSeatDocuments;
    }

    public Document createTicketDocument(Ticket ticket) {
        Document ticketDoc = new Document().append("price", ticket.getPrice())
                .append("requestExtraWeight", ticket.getRequestExtraWeight())
                .append("requestWheelChair", ticket.isRequestWheelChair()).append("type", ticket.getType())
                .append("ticketstate", ticket.getTicketstate().getClass().getSimpleName())
                .append("payment", createPaymentDocument(ticket.getPayment()))
                .append("enquiry", createEnquiryDocument(ticket.getEnquiry()))
                .append("airlineTripSeat", createAirlineTripSeats(ticket.getAirlineTripSeats()));
        return ticketDoc;
    }

    public Ticket createTicketObj(Document ticketDoc) {

        double price = ticketDoc.getDouble("price");
        int requestExtraWeight = ticketDoc.getInteger("requestExtraWeight", 0);
        boolean requestWheelChair = ticketDoc.getBoolean("requestWheelChair", false);
        String type = ticketDoc.getString("type");
        String state = ticketDoc.getString("ticketstate");

        TicketState ticketstate;
        if(state.equals("BookingState")) {
            ticketstate = new BookingState();
        } else {
            ticketstate = new EndBookingState();
        }

        ArrayList<Document> airlineTripSeatsDoc = ticketDoc.get("airlineTripSeat", new ArrayList<Document>().getClass());
        HashMap<ObjectId, Seat> airlineTripSeats = new HashMap<ObjectId, Seat>();

        airlineTripSeatsDoc.forEach((doc) -> {
            airlineTripSeats.put(doc.get("61d0ac6a14f4a48a344a7702"), doc.get("seat", Document.class));
        });

        return new Ticket(price, requestExtraWeight, requestWheelChair, type, ticketstate, airlineTripSeats, payment, passenger, enquiry);
    }
}
