package RateLimiter.TokenBucket;

import RateLimiter.RateLimter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket implements RateLimter {

    private int bucketCapacity;
    private int refreshRate;

    //AtomicInteger when you need a mutable value, you can't use Integer.
    // You can use AtomicInteger when you need a thread-safe mutable value, not just a mutable value.
    AtomicInteger currentCapacity;
    AtomicLong lastUpdatedTime;


    public TokenBucket(int bucketCapacity, int refreshRate){
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        currentCapacity = new AtomicInteger(bucketCapacity);
        lastUpdatedTime = new AtomicLong(System.currentTimeMillis());
    }

    public boolean grantAccess(){

        // before processing the request we need to check the "CurrentCapacity" of the Bucket
        refreshBucket();

        if(currentCapacity.get() > 0){

            // decrementAndGet() is an inbuilt method in java that decreases the previous value by one and
            // returns the value after updation
            currentCapacity.decrementAndGet();
            return true;
        }
        return false;
    }

    public void refreshBucket(){

        long currentTime = System.currentTimeMillis();
        // divided by "1000" to convert into second as refresh rate is per second
        int tokensToBeAdded = ((int)(currentTime - lastUpdatedTime.get()) / 1000) * refreshRate;
        int currCapacity = Math.min(currentCapacity.get() + tokensToBeAdded, bucketCapacity);
        currentCapacity.getAndSet(currCapacity);
        lastUpdatedTime.getAndSet(currentTime);
    }
}
