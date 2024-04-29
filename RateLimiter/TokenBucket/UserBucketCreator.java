package RateLimiter.TokenBucket;

import java.util.HashMap;
import java.util.Map;
public class UserBucketCreator {

    Map<Integer, TokenBucket> bucket;

    public UserBucketCreator(int id){
        bucket = new HashMap<>();
        bucket.put(id, new TokenBucket(10, 1));
    }

    public void accessAppication(int id){

        if(bucket.get(id).grantAccess()){
            System.out.println(Thread.currentThread().getName()+ " able to access the application");
        }
        else{
            System.out.println(Thread.currentThread().getName()+" Too many request, please try after sometime");
        }
    }
}
