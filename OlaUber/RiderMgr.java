package OlaUber;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RiderMgr {
    private static RiderMgr riderMgrInstance = null;
    private static final Lock mtx = new ReentrantLock();
    private Map<String, Rider> ridersMap;

    private RiderMgr() {
        ridersMap = new HashMap<>();
    }


    // The double-checked locking pattern ensures thread safety during the creation of the singleton instance.
    // double check locking
    // lock to make it thread safe while creating we need to put lock so that other threads don't access it
    // we kno lock is expensive but we are putting lock only for one time

    //** Way1
//    public static RiderMgr getRiderMgr() {
//        if (riderMgrInstance == null) {
//            mtx.lock();
//            try {
//                if (riderMgrInstance == null) {
//                    riderMgrInstance = new RiderMgr();
//                }
//            } finally {
//                mtx.unlock();
//            }
//        }
//        return riderMgrInstance;
//    }

    //** Way2
//    public static RiderMgr getRiderMgr(){
//
//        if(riderMgrInstance == null){
//            mtx.lock();
//            if(riderMgrInstance == null){
//                riderMgrInstance = new RiderMgr();
//            }
//            mtx.unlock();
//        }
//        return riderMgrInstance;
//    }

    //**Way3
    public static RiderMgr getRiderMgr(){

        if(riderMgrInstance == null){

            synchronized (RiderMgr.class){
                if(riderMgrInstance == null){
                    riderMgrInstance = new RiderMgr();
                }
            }
        }
        return riderMgrInstance;
    }


    public void addRider(String pRiderName, Rider pRider) {
        ridersMap.put(pRiderName, pRider);
    }

    public Rider getRider(String pRiderName) {
        return ridersMap.get(pRiderName);
    }

    // Define Rider class if it's not already defined
    // class Rider {
    //    Add Rider class definition here
    // }
}

