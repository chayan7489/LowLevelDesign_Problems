package OlaUber;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TripMgr {
    private static TripMgr tripMgrInstance = null;
    private static final Lock mtx = new ReentrantLock();
    private Map<Integer, Trip> tripsInfo;
    private Map<Integer, TripMetaData> tripsMetaDataInfo;

    private TripMgr() {
        tripsInfo = new HashMap<>();
        tripsMetaDataInfo = new HashMap<>();
    }

    public static TripMgr getTripMgr() {

        if (tripMgrInstance == null) {
            mtx.lock();
            try {
                if (tripMgrInstance == null) {
                    tripMgrInstance = new TripMgr();
                }
            } finally {
                mtx.unlock();
            }
        }
        return tripMgrInstance;
    }

    public void createTrip(Rider pRider, Location pSrcLoc, Location pDstLoc) {

        TripMetaData metaData = new TripMetaData(pSrcLoc, pDstLoc, pRider.getRating());
        StrategyMgr strategyMgr = StrategyMgr.getStrategyMgr();
        PricingStrategy pricingStrategy = strategyMgr.determinePricingStrategy(metaData);
        DriverMatchingStrategy driverMatchingStrategy = strategyMgr.determineMatchingStrategy(metaData);

        Driver driver = driverMatchingStrategy.matchDriver(metaData);
        double tripPrice = pricingStrategy.calculatePrice(metaData);

        Trip trip = new Trip(pRider, driver, pSrcLoc, pDstLoc, tripPrice, pricingStrategy, driverMatchingStrategy);
        int tripId = trip.getTripId();
        tripsInfo.put(tripId, trip);
        tripsMetaDataInfo.put(tripId, metaData);
    }

    public Map<Integer, Trip> getTripsMap() {
        return tripsInfo;
    }
}

