package RateLimiter.LeakyBucket;

import RateLimiter.RateLimter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LeakyBucket implements RateLimter {

    // BlockingQueue is threadSafe
    BlockingQueue<Integer> queue;

    public LeakyBucket(int capacity){
        queue = new LinkedBlockingQueue<>(capacity);
    }

    public boolean grantAccess(){

        // here we are adding '1' request at a time. You can also add the parameter passed to this function
        // "remainingCapacity" is function of BlockingQueue
        if(queue.remainingCapacity() > 0){
            queue.add(1);
            return true;
        }
        return false;
    }
}
