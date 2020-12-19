public class ChildrensPrice {
    static class ChildrenPrice extends Price {
        public int getPriceCode() {
            return Movie.CHILDRENS;
        }

        @Override
        public int getFrequentRenterPoints(int daysRented) {
            return 1;
        }

        @Override
        double getCharge(int daysRented) {
            double result = 0;
            result += 1.5;
            if (daysRented > 3) {
                result += (daysRented - 3) * 1.5;
            }
            return result;
        }

    }
}
