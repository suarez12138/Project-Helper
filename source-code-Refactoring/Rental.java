class Rental {
    private Movie movie;

    private int _daysRented;
    public Rental(Movie movie, DateRange dataRange) {
        this.movie = movie;
        _daysRented = (int)((dataRange.getEnd().getTime() - dataRange.getStart().getTime()) / (1000 * 60 * 60 * 24));
    }
    public int getDaysRented() {
        return _daysRented;
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public int getPriceCode() {
        return movie.getPriceCode();
    }

    public double getCharge() {
        //determine amounts for each line
        return movie.getCharge(_daysRented);
    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(this._daysRented);
    }
}
