public class TouristVoucher implements Comparable<TouristVoucher> {
    private String country;
    private int duration;
    private int price;

    TouristVoucher(String country, int duration, int price) {
        setCountry(country);
        setDuration(duration);
        setPrice(price);
    }

    private void setCountry(String country) {
        this.country = country;
    }

    private int getDuration() {
        return duration;
    }

    private void setDuration(int duration) {
        this.duration = duration;
    }

    private int getPrice() {
        return price;
    }

    private void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "country = '" + country + '\'' +
                ", duration = " + duration +
                ", price = " + price;
    }

    public int compareTo(TouristVoucher touristVoucher) {
        if (this.duration > touristVoucher.getDuration())
            return 1;
        if (this.duration < touristVoucher.getDuration())
            return -1;
        if (this.duration == touristVoucher.getDuration())
            return 0;
        return 0;
    }

    public int comparePriceTo(TouristVoucher touristVoucher) {
        if (this.price > touristVoucher.getPrice())
            return 1;
        if (this.price < touristVoucher.getPrice())
            return -1;
        if (this.price == touristVoucher.getPrice())
            return 0;
        return 0;
    }
}
