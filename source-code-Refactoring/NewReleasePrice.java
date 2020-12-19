public class NewReleasePrice {
    static class newReleasePrice extends Price {
        public int getPriceCode() {
            return Movie.NEW_RELEASE;
        }

        @Override
        public int getFrequentRenterPoints(int daysRented) {
            int frequentRenterPoints = 1;
            if (daysRented > 1) {
                frequentRenterPoints++;
            }
            return frequentRenterPoints;
        }

        @Override
        double getCharge(int daysRented) {
            double result = 0;
            result += daysRented * 3;
            return result;
        }
    }
}
