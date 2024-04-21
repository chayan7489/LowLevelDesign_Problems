package EcommerceApplication.service;

import EcommerceApplication.exception.EcommerceException;
import EcommerceApplication.model.Buyer;


public interface BuyerService {
    String addBuyer(Buyer buyer) throws EcommerceException;
    Buyer getBuyer(String buyerId) throws EcommerceException;
}
