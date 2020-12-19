public abstract class Price {
    public abstract int getPriceCode();


    public abstract int getFrequentRenterPoints(int daysRented);

    abstract double getCharge(int daysRented);
}
