package RateLimiter.SlidingWindow;

import RateLimiter.RateLimter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindow implements RateLimter{

    // ConcurrentLinkedQueue class is a thread-safe queue which is unbounded.
    Queue<Long> slidingWindow;
    int timeWindowInSeconds;
    int bucketCapacity;

    public SlidingWindow(int timeWindowInSeconds, int bucketCapacity) {
        slidingWindow = new ConcurrentLinkedQueue<>();
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.bucketCapacity = bucketCapacity;
    }

    @Override
    public boolean grantAccess() {
        long currentTime = System.currentTimeMillis();
        checkAndUpdateQueue(currentTime);
        if(slidingWindow.size() < bucketCapacity){
            slidingWindow.offer(currentTime);
            return true;
        }
        return false;
    }

    private void checkAndUpdateQueue(long currentTime) {

        if(slidingWindow.isEmpty()) return;

        long calculatedTime = (currentTime - slidingWindow.peek()) / 1000;
        while(calculatedTime >= timeWindowInSeconds){
            slidingWindow.poll();
            if(slidingWindow.isEmpty()) break;
            calculatedTime = (currentTime - slidingWindow.peek()) / 1000;
        }
    }
}
