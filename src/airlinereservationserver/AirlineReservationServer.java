/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package airlinereservationserver;

import java.util.ArrayList;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.types.ObjectId;

import database.DatabaseConnection;

import models.airline.Airplane;
import models.airline.Airport;
import models.airline.Crew;
import models.airline.Host;
import models.airline.airlineTrip.AirlineTrip;
import models.airline.airlineTrip.AirlineTripDetatils;
import models.ticket.Payment;
import models.ticket.Ticket;
import models.user.Passenger;
import models.user.Pilot;
import models.user.User;

public class AirlineReservationServer {

    public static void main(String[] args) {
        DatabaseConnection.createDatabaseConnection();
        MongoDatabase mongoDatabase = DatabaseConnection.getMongoDatabase();
        System.out.println("server is running");

        // ADD AIRPORT TEST
        // Airport a = new Airport();
        // ArrayList<Integer> t = new ArrayList<Integer>();
        // t.add(1);
        // t.add(2);

        // ArrayList<Integer> h = new ArrayList<Integer>();
        // h.add(1);
        // h.add(2);
        // a.addAirport("London", "UK", t, h);

        // ADD AIRPLANE TEST
        // Airplane a = new Airplane();
        // a.addAirplane("Jumbo", "Big", "Intermediate", 40, 15, 40, 2000.0);

        // ADD HOST TEST
        // Host h = new Host();
        // ArrayList<String> l = new ArrayList<String>();
        // l.add("Arabic");
        // l.add("English");
        // h.addHost("A123456", "Mayar", l);

        // ADD PILOT TEST
        // Pilot p = new Pilot("B372037", "Mayar", "Hani", "10/11/2000", "0212020102",
        // 'M', "Seif", "Mayar@test", "12345", "Medium");
        // p.insertPilot();

        // ADD CREW TEST
        // Pilot p1 = new Pilot(new ObjectId("61cf17c214f4a45aac2d8a8e"),"A1234343",
        // "Seif", "Ahmad", "2000-11-10", "0212020102",
        // 'M', "Seif", "Seif@test", "12345", "High");

        // Pilot p2 = new Pilot(new ObjectId("61cf1a0514f4a4328c319f6e"), "B372037",
        // "Mayar", "Hani", "2000-11-10", "0212020102",
        // 'M', "Seif", "Mayar@test", "12345", "Medium");

        // ArrayList<Pilot> coPilots = new ArrayList<Pilot>();
        // coPilots.add(p2);

        // ArrayList<String> l = new ArrayList<String>();
        // l.add("Arabic");
        // l.add("English");
        // Host h = new Host(new ObjectId("61cf046e14f4a4860408e395"), "A123456",
        // "Mayar", l);
        // ArrayList<Host> hosts = new ArrayList<Host>();
        // hosts.add(h);

        // ArrayList<String> airplaneLevels = new ArrayList<String>();
        // airplaneLevels.add("Advanced");

        // Crew c = new Crew();
        // c.createCrew(p1, coPilots, hosts, airplaneLevels);

        // LOGIN TEST
        // User p = new Pilot();
        // p.login("Seif@test", "12345");

        // // GET ALL AIRPLANES TEST
        // Airplane airplane = new Airplane();
        // ArrayList<Airplane> airplanes = airplane.getAirplanes();
        // // GET ALL AIRPORTS TEST
        // Airport airport = new Airport();
        // ArrayList<Airport> airports = airport.getAirports();


        // // GET ALL Hosts TEST
        // // Host host = new Host();
        // // host.getAllHosts();

        // // GET ALL CREWS TEST
        // Crew crew = new Crew();
        // ArrayList<Crew> crews = crew.getCrews();

        // // INSERT AIRLINETRIP TEST
        // AirlineTrip airlineTrip = new AirlineTrip();
        // airlineTrip.addAirlineTrip(airplanes.get(0), airports.get(0), airports.get(1), new AirlineTripDetatils("2017-01-13T17:09:42.411", "2017-01-13T17:09:42.411", 1, 1), 200, crews.get(0));

        // // INSERT PASSENGER TEST
        // Passenger pa1 = new Passenger("A43454343", "Shiko", "Ahmad", "2000-11-10", "010220910", 'M', "shiko", "shiko@test.com", "123");
        // pa1.insertPassenger();

        // LOGIN TEST
        User p = new Passenger();
        Passenger pa = (Passenger) p.login("karim@test.com", "123");

        // ArrayList<String> companionsEmails = new ArrayList<String>();
        // companionsEmails.add("shiko@test.com");
        // pa.addCompanions(companionsEmails);

        AirlineTrip a = new AirlineTrip();
        ArrayList<AirlineTrip> airlines = new ArrayList<AirlineTrip>();
        airlines.add(a.getAirlineTripsBy("Cairo", "London").get(0));
        Ticket t = new Ticket();
        t.bookTicket(airlines, pa, 0, false, "Economy", new Payment("83938290832", "Karim", "2000-11-10"));
    }

}
