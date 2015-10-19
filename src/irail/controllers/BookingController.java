package irail.controllers;

import irail.View;
import irail.models.Birth;
import irail.views.BookingView;

/**
 *
 * @author jayadeep
 */
public class BookingController {
    Birth birth;
    BookingView bookingView;
    String date;
    
    public BookingController(Birth b,String d){
        birth = b;
        date = d;
        bookingView = new BookingView();
        View.addToDesk(bookingView);
    }
    
}
