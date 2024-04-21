package EcommerceApplication.service.Impl;

import EcommerceApplication.exception.EcommerceException;
import EcommerceApplication.model.PaymentMode;
import EcommerceApplication.model.PinCodeServiceability;
import EcommerceApplication.repository.PincodeServiceabilityRepository;
import EcommerceApplication.service.PincodeServiceabilityService;

import java.util.HashMap;


public class PincodeServiceabilityServiceImpl  implements PincodeServiceabilityService {

    PincodeServiceabilityRepository pincodeServiceabilityRepository;

    public PincodeServiceabilityServiceImpl(PincodeServiceabilityRepository pincodeServiceabilityRepository){
        this.pincodeServiceabilityRepository = pincodeServiceabilityRepository;
    }


    @Override
    public Boolean createPinCodeServiceability(String sourcePinCode, String destinationPinCode, PaymentMode paymentMode) throws EcommerceException {
        PinCodeServiceability pinCodeServiceability = new PinCodeServiceability(destinationPinCode, paymentMode);
        return pincodeServiceabilityRepository.createPinCodeServiceability(sourcePinCode, pinCodeServiceability);

    }

    @Override
    public Boolean checkIsSourceAndDestPinCodeMatchesForPaymentType(String sourcePinCode, String destinationPinCode, PaymentMode paymentMode) throws EcommerceException {
        HashMap<String, PaymentMode> allDestinationPincodes = pincodeServiceabilityRepository.getAllDestinationPincodes(sourcePinCode);
        return allDestinationPincodes.containsKey(destinationPinCode) &&
                paymentMode.equals(allDestinationPincodes.get(destinationPinCode));
    }
}
