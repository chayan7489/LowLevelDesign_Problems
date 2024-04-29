package RateLimiter.LeakyBucket;

import java.util.Map;
import java.util.HashMap;

public class UserBucketCreator {

    Map<Integer, LeakyBucket> bucket;

    public UserBucketCreator(int id){
        bucket = new HashMap<>();
        // for every "UserId" we have bucket capacity of "10"
        bucket.put(id, new LeakyBucket(10));
    }

    public void accessAppication(int id){

        // this is working in "Serial Mode"
//        if(bucket.get(id).grantAccess()){
//            System.out.println("able to access the application");
//        }
//        else{
//            System.out.println("Too many request, please try after sometime");
//        }

        // this is working in "Parallel Mode"
        if(bucket.get(id).grantAccess()){
            System.out.println(Thread.currentThread().getName()+ " able to access the application");
        }
        else{
            System.out.println(Thread.currentThread().getName()+" Too many request, please try after sometime");
        }
    }
}
