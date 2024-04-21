package EcommerceApplication.service.Impl;

import EcommerceApplication.exception.EcommerceException;
import EcommerceApplication.model.ErrorCode;
import EcommerceApplication.model.Product;
import EcommerceApplication.repository.ProductRepository;
import EcommerceApplication.service.ProductService;
import EcommerceApplication.utils.ErrorCodeMap;

public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public String addProduct(Product product) throws EcommerceException {
        Product createdProduct = productRepository.createProduct(product);
        return createdProduct.getProductId();
    }

    @Override
    public Product getProduct(String productId) throws EcommerceException {
        return productRepository.getProduct(productId);
    }
    @Override
    public boolean checkInventory(int orderedQuantity, String productId){

        // "Synchronization" in Java is the process that allows only one thread at
        // a particular time to complete a given task entirely.
        // Note: Synchronized blocks in Java are marked with the synchronized keyword.
        // **Eg, when there is only one product left and 2 people (thread)
        // try to buy it at the same time then it will lead to "RACE CONDITION"

        synchronized (this) {
            Product product = productRepository.getProduct(productId);
            if (orderedQuantity <= product.getProductQuantity()) {
                product.setProductQuantity(product.getProductQuantity() - orderedQuantity);
                return true;
            } else {
                throw new EcommerceException(ErrorCode.IN_SUFFICIENT_INVENTORY, ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.IN_SUFFICIENT_INVENTORY));
            }
        }
    }
}
