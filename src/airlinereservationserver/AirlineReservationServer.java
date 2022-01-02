/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package airlinereservationserver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
import models.user.Admin;
import models.user.Passenger;
import models.user.Pilot;
import models.user.User;
import rmi.AdminInterface;
import rmi.AirplaneInterface;
import rmi.AirportInterface;
import rmi.CrewInterface;
import rmi.HostInterface;
import rmi.PassengerInterface;
import rmi.PilotInterface;
import rmi.UserInterface;

public class AirlineReservationServer {

    public static void main(String[] args) {
        DatabaseConnection.createDatabaseConnection();
        MongoDatabase mongoDatabase = DatabaseConnection.getMongoDatabase();
        System.out.println("server is running");

        try {
            // My remote object [Skeleton]
            AirportInterface airportInterface = new Airport();
            HostInterface hostInterface = new Host();
            AirplaneInterface airplaneInterface = new Airplane();
            PilotInterface pilotInterface = new Pilot();
            PassengerInterface passengerInterface = new Passenger();
            AdminInterface adminInterface = new Admin();
            CrewInterface crewInterface = new Crew();

            // My RMI Registry
            Registry registry = LocateRegistry.createRegistry(1099);

            // Add my object to the RMI Registry
            registry.bind("airport", airportInterface);
            registry.bind("host", hostInterface);
            registry.bind("airplane", airplaneInterface);
            registry.bind("pilot", pilotInterface);
            registry.bind("passenger", passengerInterface);
            registry.bind("admin", adminInterface);
            registry.bind("crew", crewInterface);
            System.out.println("My SERVER is ready...");
        } catch (Exception ex) {
            System.out.println(ex);
        }

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

        // // ADD HOST TEST
        // Host h = new Host();
        // ArrayList<String> l = new ArrayList<String>();
        // l.add("Arabic");
        // l.add("Spain");
        // h.addHost("A12e43456", "Mariam", l);

        // REMOVE HOST TEST
        // Host host;
        // try {
        //     host = new Host();
        //     host.getAllHosts().get(1).removeHost();
        // } catch (RemoteException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }


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
        // GET ALL AIRPORTS TEST
        // Airport airport;
        // try {
        //     airport = new Airport();
        //     ArrayList<AirportInterface> airports = airport.getAirports();
        //     System.out.println(((Airport)airports.get(0)).getName());
        // } catch (RemoteException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        // Airport airport;
        // try {
        //     airport = new Airport();
        //     ArrayList<AirportInterface> airports = airport.getAirports();
        //     System.out.println(airports.get(0).getName());

        // } catch (RemoteException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }


        // // GET ALL Hosts TEST
        // // Host host = new Host();
        // // host.getAllHosts();

        // GET ALL CREWS TEST
        // Crew crew;
        // try {
        //     crew = new Crew();
        //     ArrayList<CrewInterface> crews = crew.getCrews();
        //     System.out.println(((Crew)crews.get(0)).getCrewId());
        // } catch (RemoteException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }


        // // INSERT AIRLINETRIP TEST
        // AirlineTrip airlineTrip = new AirlineTrip();
        // airlineTrip.addAirlineTrip(airplanes.get(0), airports.get(0), airports.get(1), new AirlineTripDetatils("2017-01-13T17:09:42.411", "2017-01-13T17:09:42.411", 1, 1), 200, crews.get(0));

        // // INSERT PASSENGER TEST
        // Passenger pa1 = new Passenger("A43454343", "Shiko", "Ahmad", "2000-11-10", "010220910", 'M', "shiko", "shiko@test.com", "123");
        // pa1.insertPassenger();

        // //LOGIN TEST
        // User p;
        // try {
        //     p = new Passenger();
        //     Passenger pa = (Passenger) p.login("karim@test.com", "123");
        //     // GET COMPANION TEST
        //     System.out.println(((Passenger) pa.getPassengerCompanions().get(0)).getFname());
        // } catch (RemoteException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        // System.out.println(pa.getPassengerTickets().get(0).getPrice());

        // // REMOVE COMPANION TEST
        // pa.removeCompanion(new ObjectId("61d0912414f4a458ecf7a79a"));

        //CANCEL TICKET TEST
        // pa.cancelTicket(1);

        //ADD COMAPNIONS TEST
        // ArrayList<String> companionsEmails = new ArrayList<String>();
        // companionsEmails.add("shiko@test.com");
        // pa.addCompanions(companionsEmails);

        // //BOOK TICKET TEST
        // AirlineTrip a = new AirlineTrip();
        // ArrayList<AirlineTrip> airlines = new ArrayList<AirlineTrip>();
        // airlines.add(a.getAirlineTripsBy("Cairo", "London").get(0));
        // Ticket t = new Ticket();
        // t.bookTicket(airlines, pa, 0, false, "Economy", new Payment("83938290832", "Karim", "2000-11-10"));

        // AirlineTrip a = new AirlineTrip();
        // a = a.getAirlineTripBy(new ObjectId("61d0e80a14f4a46a886dc233"));

        // a.cancelAirlineTrip();
    }

}
