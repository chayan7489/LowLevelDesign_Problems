package OlaUber;


// The ReentrantLock is a synchronization mechanism provided by the Java concurrency library
// (java.util.concurrent.locks package) for managing access to shared resources in a multi-threaded environment.
// It allows threads to acquire and release locks in a flexible and explicit manner.
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DriverMgr {
    private static DriverMgr driverMgrInstance = null;
    private static final Lock mtx = new ReentrantLock();
    private Map<String, Driver> driversMap;

    private DriverMgr() {

        this.driversMap = new HashMap<>();
    }


    // Double-Checked Locking
    // "mtx.lock()" This ensures that only one thread can execute the following block of code at a time,
    // preventing multiple threads from creating multiple instances of DriverMgr concurrently.
    // This is a try-finally block. It ensures that the mutex is always unlocked, even if an exception occurs within the try block.
    // This is important to prevent deadlocks or resource leaks.

    public static DriverMgr getDriverMgr() {
        if (driverMgrInstance == null) {
            mtx.lock();
            try {
                if(driverMgrInstance == null) {
                    driverMgrInstance = new DriverMgr();
                }
            } finally {
                mtx.unlock();
            }
        }
        return driverMgrInstance;
    }

    public void addDriver(String pDriverName, Driver pDriver) {
        driversMap.put(pDriverName, pDriver);
    }

    public Driver getDriver(String pDriverName) {
        return driversMap.get(pDriverName);
    }


    // returning this map to compare drivers in "DriverMatchingStrategy"
    public Map<String, Driver> getDriversMap() {
        return driversMap;
    }

    // Define Driver class if it's not already defined
    // class Driver {
    //    Add Driver class definition here
    // }
}

