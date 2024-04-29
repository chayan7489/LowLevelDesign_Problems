package OlaUber;

public class Driver {
    private String name;
    private boolean avail;
    private Util.RATING rating;

    public Driver(String pName, Util.RATING pRating) {
        this.name = pName;
        this.rating = pRating;
        this.avail = false;
    }

    public void updateAvail(boolean pAvail) {
        this.avail = pAvail;
    }

    public String getDriverName() {
        return name;
    }

    public Util.RATING getRating() {
        return rating;
    }
}

