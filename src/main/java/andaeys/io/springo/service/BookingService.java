package andaeys.io.springo.service;

import andaeys.io.springo.entity.Booking;
import andaeys.io.springo.handler.ResourceNotFoundException;
import andaeys.io.springo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getAllCurrentMonthBookings() {
        return bookingRepository.findAll()
                .stream().filter(booking ->
                        booking.getCheckIn().getMonthValue() == LocalDate.now().getMonthValue()
                ).collect(Collectors.toList());
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("booking id "+id+" not found"));
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("booking id "+id+" not found"));
       booking.setGuestName(bookingDetails.getGuestName());
       booking.setHotelName(bookingDetails.getHotelName());
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
