package EcommerceApplication.service;

import EcommerceApplication.exception.EcommerceException;
import EcommerceApplication.model.PaymentMode;
public interface PincodeServiceabilityService {

    Boolean createPinCodeServiceability(String sourcePinCode,
                                        String destinationPinCode,
                                        PaymentMode paymentMode) throws EcommerceException;
    Boolean checkIsSourceAndDestPinCodeMatchesForPaymentType(
            String sourcePinCode,
            String destinationPinCode,
            PaymentMode paymentMode
    ) throws EcommerceException;

}
