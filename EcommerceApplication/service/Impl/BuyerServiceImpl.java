package EcommerceApplication.service.Impl;

import EcommerceApplication.exception.EcommerceException;
import EcommerceApplication.model.Buyer;
import EcommerceApplication.repository.BuyerRepository;
import EcommerceApplication.service.BuyerService;

public class BuyerServiceImpl implements BuyerService {

    BuyerRepository buyerRepository;

    public BuyerServiceImpl(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }

    @Override
    public String addBuyer(Buyer buyer) throws EcommerceException {
        Buyer createdBuyer = buyerRepository.createBuyer(buyer);
        return createdBuyer.getBuyerId();
    }

    @Override
    public Buyer getBuyer(String buyerId) throws EcommerceException {
        return buyerRepository.getBuyer(buyerId);
    }
}
