package EcommerceApplication;

import EcommerceApplication.exception.EcommerceException;
import EcommerceApplication.model.Address;
import EcommerceApplication.model.Buyer;
import EcommerceApplication.model.Order;
import EcommerceApplication.model.PaymentMode;
import EcommerceApplication.model.Product;
import EcommerceApplication.repository.BuyerRepository;
import EcommerceApplication.repository.OrderRepository;
import EcommerceApplication.repository.PincodeServiceabilityRepository;
import EcommerceApplication.repository.ProductRepository;
import EcommerceApplication.service.BuyerService;
import EcommerceApplication.service.Impl.BuyerServiceImpl;
import EcommerceApplication.service.Impl.OrderServiceImpl;
import EcommerceApplication.service.Impl.PincodeServiceabilityServiceImpl;
import EcommerceApplication.service.Impl.ProductServiceImpl;
import EcommerceApplication.service.PincodeServiceabilityService;
import EcommerceApplication.service.ProductService;

public class Driver {

    public static void main(String[] args) {
        BuyerRepository buyerRepository = new BuyerRepository();
        OrderRepository orderRepository = new OrderRepository();
        ProductRepository productRepository = new ProductRepository();
        PincodeServiceabilityRepository pincodeServiceabilityRepository = new PincodeServiceabilityRepository();

        BuyerService buyerService = new BuyerServiceImpl(buyerRepository);
        ProductService productService = new ProductServiceImpl(productRepository);
        PincodeServiceabilityService pincodeServiceabilityService = new PincodeServiceabilityServiceImpl(pincodeServiceabilityRepository);
        OrderServiceImpl orderService = new OrderServiceImpl(
                orderRepository, productService, pincodeServiceabilityService, buyerService);


        //##Case 1:When the Product class PinCode matches the Buyer class PinCode and there is available inventory,
        // and the PaymentMode for the Order class matches the PaymentMode of PinCodeServiceability class associated
        // with the that PinCode.


        Address address1 =  new Address("Dwarka","Delhi","531162");
        Product product1 = new Product("T-shirt", 10, address1);
        String product1Id = productService.addProduct(product1);
        Buyer buyer1 = new Buyer("Chayan", address1);
        String buyer1Id = buyerService.addBuyer(buyer1);
        pincodeServiceabilityService.createPinCodeServiceability("531162","531162", PaymentMode.PREPAID);
        Order order1 = new Order(product1Id, buyer1Id, 5, PaymentMode.PREPAID);

        try {
            String order1Id = orderService.addOrder(order1);
            System.out.println("Order1 Placed Sucessfull "+ order1Id);
        }catch (EcommerceException e){
            System.out.println(e.getErrorCode()+": "+e.getErrorMessage());
        }


        //**************************************************************************************************************

        //##Case 2: When the Product class PinCode matches the Buyer class PinCode and there is available inventory,
        // and the PaymentMode for the Order class does not match with the PaymentMode of PinCodeServiceability class
        // associated with the that PinCode.


//        Address address1 =  new Address("Dwarka","Delhi","531162");
//        Product product1 = new Product("T-shirt", 10, address1);
//        String product1Id = productService.addProduct(product1);
//        Buyer buyer1 = new Buyer("Chayan", address1);
//        String buyer1Id = buyerService.addBuyer(buyer1);
//        pincodeServiceabilityService.createPinCodeServiceability("531162","531162", PaymentMode.PREPAID);
//        Order order1 = new Order(product1Id, buyer1Id, 5, PaymentMode.COD);
//        try {
//            String order1Id = orderService.addOrder(order1);
//            System.out.println("Order1 Placed Sucessfull "+ order1Id);
//        }catch (EcommerceException e){
//            System.out.println(e.getErrorCode()+": "+e.getErrorMessage());
//        }


        //**************************************************************************************************************


        //##Case 3: When the Product class PinCode does not match with the Buyer class PinCode.

//        Address address1 =  new Address("Dwarka","Delhi","531162");
//        Address address2 =  new Address("Amritsar","Punjab","500082");
//        Product product1 = new Product("T-shirt", 10, address1);
//        String product1Id = productService.addProduct(product1);
//        Buyer buyer1 = new Buyer("Chayan", address2);
//        String buyer1Id = buyerService.addBuyer(buyer1);
//        pincodeServiceabilityService.createPinCodeServiceability("531162","531162", PaymentMode.PREPAID);
//        Order order1 = new Order(product1Id, buyer1Id, 5, PaymentMode.PREPAID);
//
//        try {
//            String order1Id = orderService.addOrder(order1);
//            System.out.println("Order1 Placed Sucessfull "+ order1Id);
//        }catch (EcommerceException e){
//            System.out.println(e.getErrorCode()+": "+e.getErrorMessage());
//        }


        //**************************************************************************************************************

        //##Case 4: The order you are attempting to place lacks sufficient inventory.


//        Address address1 =  new Address("Dwarka","Delhi","531162");
//        Product product1 = new Product("T-shirt", 10, address1);
//        String product1Id = productService.addProduct(product1);
//        Buyer buyer1 = new Buyer("Chayan", address1);
//        String buyer1Id = buyerService.addBuyer(buyer1);
//        pincodeServiceabilityService.createPinCodeServiceability("531162","531162", PaymentMode.PREPAID);
//        Order order1 = new Order(product1Id, buyer1Id, 5, PaymentMode.PREPAID);
//        Order order2 = new Order(product1Id, buyer1Id, 6, PaymentMode.PREPAID);
//
//        try {
//            String order1Id = orderService.addOrder(order1);
//            System.out.println("Order1 Placed Sucessfull "+ order1Id);
//
//            String order2Id = orderService.addOrder(order2);
//            System.out.println("Order3 Placed Sucessfull "+ order2Id);
//
//        }catch (EcommerceException e){
//            System.out.println(e.getErrorCode()+": "+e.getErrorMessage());
//        }


        //**************************************************************************************************************


        //##Case 5: When you attempt to create a Product that already exists.


//        Address address1 =  new Address("Dwarka","Delhi","531162");
//        Product product1 = new Product("T-shirt", 10, address1);
//        String product1Id = productService.addProduct(product1);
//        String product2Id = productService.addProduct(product1);

        //**************************************************************************************************************


        //##Case 6: When you attempt to create a Buyer that already exists.

//        Address address1 =  new Address("Dwarka","Delhi","531162");
//        Product product1 = new Product("T-shirt", 10, address1);
//        String product1Id = productService.addProduct(product1);
//        Buyer buyer1 = new Buyer("Chayan", address1);
//        String buyer1Id = buyerService.addBuyer(buyer1);
//        String buyer2Id = buyerService.addBuyer(buyer1);



    }
}
