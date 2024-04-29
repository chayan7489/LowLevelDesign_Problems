package OlaUber;

public class Rider {
    private String name;
    private Util.RATING rating;

    public Rider(String pName, Util.RATING pRating) {
        name = pName;
        rating = pRating;
    }

    public String getRiderName() {
        return name;
    }

    public Util.RATING getRating() {
        return rating;
    }
}
