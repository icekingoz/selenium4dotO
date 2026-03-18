package base;

public class BookingDates {

    private String checking;
    private String checkout;


    public BookingDates(String checkout, String checking) {
        this.checkout = checkout;
        this.checking = checking;
    }

    @Override
    public String toString() {
        return "BookingDates{" +
                "checking='" + checking + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }

    public String getChecking() {
        return checking;
    }

    public void setChecking(String checking) {
        this.checking = checking;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}
