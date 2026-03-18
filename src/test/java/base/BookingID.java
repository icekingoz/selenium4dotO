package base;

public class BookingID {
    private int bookingId;
    private Booking booking;

    public BookingID() {

    }

    public BookingID(int bookingId, Booking booking) {
        this.bookingId = bookingId;
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingID{" +
                "bookingId=" + bookingId +
                ", booking=" + booking +
                '}';
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    //HTTP/1.1 200 OK
//
//{
//    "bookingid": 1,
//    "booking": {
//        "firstname": "Jim",
//        "lastname": "Brown",
//        "totalprice": 111,
//        "depositpaid": true,
//        "bookingdates": {
//            "checkin": "2018-01-01",
//            "checkout": "2019-01-01"
//        },
//        "additionalneeds": "Breakfast"
//    }
//}




}
