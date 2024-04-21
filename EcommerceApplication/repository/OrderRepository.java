package EcommerceApplication.repository;

import EcommerceApplication.exception.EcommerceException;
import EcommerceApplication.model.ErrorCode;
import EcommerceApplication.model.Order;
import EcommerceApplication.utils.ErrorCodeMap;

import java.util.HashMap;

public class OrderRepository {

    // orderId v/s Order
    HashMap<String, Order> orders;

    public OrderRepository(){
        orders = new HashMap<>();
    }

    public Order createOrder(Order order) throws EcommerceException{

        if(orders.get(order.getOrderId()) != null){
            throw new EcommerceException(
                    ErrorCode.ORDER_CREATION_FAILED, ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.PRODUCT_ALREADY_CREATED)
            );
        }
        orders.put(order.getOrderId(), order);
        return order;
    }

    public Order getOrder(String orderId){

        return orders.get(orderId);
    }

}
