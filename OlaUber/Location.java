package OlaUber;

public class Location {
    public double latitude;
    public double longitude;

    public Location(double pLatitude, double pLongitude) {
        latitude = pLatitude;
        longitude = pLongitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

