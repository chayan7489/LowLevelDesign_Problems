package EcommerceApplication.service.Impl;

import EcommerceApplication.exception.EcommerceException;
import EcommerceApplication.model.ErrorCode;
import EcommerceApplication.model.Order;
import EcommerceApplication.repository.OrderRepository;
import EcommerceApplication.service.BuyerService;
import EcommerceApplication.service.OrderService;
import EcommerceApplication.service.PincodeServiceabilityService;
import EcommerceApplication.service.ProductService;
import EcommerceApplication.utils.ErrorCodeMap;

public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;
    ProductService productService;
    PincodeServiceabilityService pincodeServiceabilityService;
    BuyerService buyerService;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            ProductService productService,
            PincodeServiceabilityService pincodeServiceabilityService,
            BuyerService buyerService){
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.pincodeServiceabilityService = pincodeServiceabilityService;
        this.buyerService = buyerService;
    }

    @Override
    public String addOrder(Order order) throws EcommerceException {
        // Check Pincode

        // check if "Product PINCODE" matches with "Buyer PINCODE"
        final String sourcePinCode = productService.getProduct(order.getProductId()).getAddress().getPinCode();
        final String destinationPinCode = buyerService.getBuyer(order.getBuyerId()).getAddress().getPinCode();

        if(!pincodeServiceabilityService.checkIsSourceAndDestPinCodeMatchesForPaymentType(
                sourcePinCode,
                destinationPinCode, order.getPaymentMode()
        )){
            throw new EcommerceException(
                    ErrorCode.PIN_CODE_UNSERVICEABLE, ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.PIN_CODE_UNSERVICEABLE)
            );
        }

        // Check Inventory
        if(productService.checkInventory(order.getQuantity(),order.getProductId())){

            // Inventory is there so Buyer can create an order
            Order createdOrder =  orderRepository.createOrder(order);
            return createdOrder.getOrderId();
        }
        throw new EcommerceException(
                ErrorCode.ORDER_CREATION_FAILED, ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.PRODUCT_ALREADY_CREATED)
        );
    }

    @Override
    public Order getOrder(String orderId) throws EcommerceException {
        return orderRepository.getOrder(orderId);
    }
}
