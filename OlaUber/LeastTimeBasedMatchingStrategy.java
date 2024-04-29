package OlaUber;

import java.util.Map;

public class LeastTimeBasedMatchingStrategy implements DriverMatchingStrategy {
    public Driver matchDriver(TripMetaData pMetaData) {

        DriverMgr driverMgr = DriverMgr.getDriverMgr();
        Map<String, Driver> driversMap = driverMgr.getDriversMap();

        if(driversMap.isEmpty()) {
            System.out.println("No drivers! What service is this huh?");
        }
        System.out.println("Using quadtree to see nearest cabs, using driver manager to get details of drivers and send notifications");
        // since below line is just an statement so everytime it will return first driver
        Driver driver = driversMap.entrySet().iterator().next().getValue(); // here we can call quadtree algo to get nearest cabs
        System.out.println("Setting " + driver.getDriverName() + " as driver");
        pMetaData.setDriverRating(driver.getRating());
        return driver;
    }
}

