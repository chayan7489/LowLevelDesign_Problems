package EcommerceApplication.service;

import EcommerceApplication.exception.EcommerceException;
import EcommerceApplication.model.Order;

public interface OrderService {
    String addOrder(Order order) throws EcommerceException;
    Order getOrder(String orderId) throws EcommerceException;
}
