package OlaUber;

public interface PricingStrategy {
    abstract double calculatePrice(TripMetaData pTripMetaData);
}

