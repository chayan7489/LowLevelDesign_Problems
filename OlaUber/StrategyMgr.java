package OlaUber;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StrategyMgr {
    private static StrategyMgr strategyMgrInstance = null;
    private static final Lock mtx = new ReentrantLock();

    private StrategyMgr() {
        // Private constructor to prevent instantiation outside the class
    }

    public static StrategyMgr getStrategyMgr() {
        if (strategyMgrInstance == null) {
            mtx.lock();
            try {
                if (strategyMgrInstance == null) {
                    strategyMgrInstance = new StrategyMgr();
                }
            } finally {
                mtx.unlock();
            }
        }
        return strategyMgrInstance;
    }

    public PricingStrategy determinePricingStrategy(TripMetaData metaData) {
        System.out.println("Based on location and other factors, setting pricing strategy");
        return new DefaultPricingStrategy();
    }

    public DriverMatchingStrategy determineMatchingStrategy(TripMetaData metaData) {
        System.out.println("Based on location and other factors, setting matching strategy");
        return new LeastTimeBasedMatchingStrategy();
    }
}

