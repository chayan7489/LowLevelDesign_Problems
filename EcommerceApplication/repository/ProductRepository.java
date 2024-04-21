package EcommerceApplication.repository;

import EcommerceApplication.exception.EcommerceException;
import EcommerceApplication.model.ErrorCode;
import EcommerceApplication.model.Product;
import EcommerceApplication.utils.ErrorCodeMap;

import java.util.HashMap;

public class ProductRepository {

    // productId v/s Product
    HashMap<String, Product> products;

    public ProductRepository(){
        products = new HashMap<>();
    }

    public Product createProduct(Product product) throws EcommerceException{
        if(products.get(product.getProductId()) != null){
            throw new EcommerceException(
                    ErrorCode.PRODUCT_ALREADY_CREATED, ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.PRODUCT_ALREADY_CREATED)
            );
        }
        products.put(product.getProductId(), product);
        return product;
    }

    public Product getProduct(String productId){

        return products.get(productId);
    }

}