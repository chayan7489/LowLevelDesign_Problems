package EcommerceApplication.model;

public class Address {
    private final String address;
    private final String city;
    private final String pinCode;


    public Address(String address, String city, String pinCode) {
        this.address = address;
        this.city = city;
        this.pinCode = pinCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPinCode() {
        return pinCode;
    }
}