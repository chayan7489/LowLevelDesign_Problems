package OlaUber;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Creating Riders and Drivers
        Rider chayanRider = new Rider("Chayan", Util.RATING.FIVE_STARS);
        Rider abhishekRider = new Rider("Abhishek", Util.RATING.FIVE_STARS);
        RiderMgr riderMgr = RiderMgr.getRiderMgr();
        riderMgr.addRider("chayan", chayanRider);
        riderMgr.addRider("abhishek", abhishekRider);

        Driver lokeshDriver = new Driver("Lokesh", Util.RATING.THREE_STARS);
        Driver rahulDriver = new Driver("Rahul", Util.RATING.FOUR_STARS);
        DriverMgr driverMgr = DriverMgr.getDriverMgr();
        driverMgr.addDriver("lokesh", lokeshDriver);
        driverMgr.addDriver("rahul", rahulDriver);

        // These details in turn will be stored in a database

        TripMgr tripMgr = TripMgr.getTripMgr();

        System.out.println("\nCreating Trip for Chayan from location (10,10) to (30,30)");
        tripMgr.createTrip(chayanRider, new Location(10, 10), new Location(30, 30));

        System.out.println("\nCreating Trip for Abhishek from location (200,200) to (500,500)");
        tripMgr.createTrip(abhishekRider, new Location(200, 200), new Location(500, 500));

        // Display all the trips created
        Map<Integer, Trip> tripsMap = tripMgr.getTripsMap();
        for (Map.Entry<Integer, Trip> entry : tripsMap.entrySet()) {
            entry.getValue().displayTripDetails();
        }
    }
}

