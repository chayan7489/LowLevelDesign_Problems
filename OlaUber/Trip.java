package OlaUber;

public class Trip {
    private Rider rider;
    private Driver driver;
    private Location srcLoc;
    private Location dstLoc;
    private Util.TRIP_STATUS status;
    private int tripId;
    private double price;
    private PricingStrategy pricingStrategy;
    private DriverMatchingStrategy driverMatchingStrategy;

    public Trip(Rider pRider, Driver pDriver, Location pSrcLoc, Location pDstLoc, double pPrice,
                PricingStrategy pPricingStrategy, DriverMatchingStrategy pDriverMatchingStrategy) {
        this.rider = pRider;
        this.driver = pDriver;
        this.srcLoc = pSrcLoc;
        this.dstLoc = pDstLoc;
        this.price = pPrice;
        this.pricingStrategy = pPricingStrategy;
        this.driverMatchingStrategy = pDriverMatchingStrategy;
        this.status = Util.TRIP_STATUS.DRIVER_ON_THE_WAY;
        // This is not thread-safe and is just for demo purposes, taken static variable and incrementing the
        // value in constructor everytime
        this.tripId = Util.nextTripId;
        Util.nextTripId++;
    }

    public int getTripId() {
        return tripId;
    }

    public void displayTripDetails() {
        System.out.println();
        System.out.println("Trip id - " + tripId);
        System.out.println("Rider - " + rider.getRiderName());
        System.out.println("Driver - " + driver.getDriverName());
        System.out.println("Price - " + price);
        System.out.println("Locations - " + srcLoc.getLatitude() + "," + srcLoc.getLongitude() +
                " and " + dstLoc.getLatitude() + "," + dstLoc.getLongitude());
    }
}

