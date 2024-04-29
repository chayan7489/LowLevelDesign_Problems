package RateLimiter.LeakyBucket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) {

        UserBucketCreator userBucketCreator = new UserBucketCreator(1);

        // this is working in "Serial Mode"
//        for(int i = 0 ; i < 12 ; i++){
//            // checking for "User Id = 1"
//            userBucketCreator.accessAppication(1);
//        }


        // this is working in "Parallel Mode" -> "MultiThreaded Environment" for "Single Instance"
        // since our Queue size is "10" so we created "10" thread"
        ExecutorService executors = Executors.newFixedThreadPool(10);

        for(int i = 0 ; i < 12 ; i++){
            executors.execute(() -> userBucketCreator.accessAppication(1));
        }
        executors.shutdown();
    }
}


// Suppose we have "Multiple Instance" -> "Distributed Environment" then in this case
// user will be able to send 30 request i.e., "10 request/instance" because each server will have
// it's own queue and bucket. And we don't want this we want to limit the user to "10" request only.
// Once those 10 request are processed only after that user is allowed to make another 10 request.
// To avoid "30 request" problem instead of making bucket in local cache we can make global bucket i.e.,
// global cache and for this we can use "Redis" so each time our application server will check in global cache

// Drawback
// Our "Leaky Bucket" implementation is not constrained by time. Suppose our rate limiter is fast to process 10 request
// then user will be allowed 10 more request and so on. But in case of password reset issue we need to block user for sometime
// before making another attempts. This is like where someone trying to hack someone's account.
// To fix this issue we will use "Token Bucket"