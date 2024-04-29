package OlaUber;

public class TripMetaData {
    private Location srcLoc;
    private Location dstLoc;
    private Util.RATING riderRating;
    private Util.RATING driverRating;

    public TripMetaData(Location pSrcLoc, Location pDstLoc, Util.RATING pRiderRating) {
        this.srcLoc = pSrcLoc;
        this.dstLoc = pDstLoc;
        this.riderRating = pRiderRating;

        // we set the driverRating once the Trip is created
        this.driverRating = Util.RATING.UNASSIGNED;
    }

    public Util.RATING getRiderRating() {
        return riderRating;
    }

    public Util.RATING getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(Util.RATING pDriverRating) {
        this.driverRating = pDriverRating;
    }
}
