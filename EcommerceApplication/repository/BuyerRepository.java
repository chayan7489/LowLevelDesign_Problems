package EcommerceApplication.repository;

import EcommerceApplication.exception.EcommerceException;
import EcommerceApplication.model.Buyer;
import EcommerceApplication.model.ErrorCode;
import EcommerceApplication.utils.ErrorCodeMap;

import java.util.HashMap;

public class BuyerRepository {

    // buyerId v/s Buyer
    HashMap<String, Buyer> buyers;

    public BuyerRepository(){

        buyers = new HashMap<>();
    }

    public Buyer createBuyer(Buyer buyer) throws EcommerceException{

        if(buyers.get(buyer.getBuyerId()) != null){
            throw new EcommerceException(
                    ErrorCode.BUYER_CREATION_FAILED, ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.BUYER_CREATION_FAILED)
            );
        }
        buyers.put(buyer.getBuyerId(), buyer);
        return buyer;
    }

    public Buyer getBuyer(String buyerId){

        return buyers.get(buyerId);
    }

}
