package andaeys.io.springo.service;

import andaeys.io.springo.entity.Booking;
import andaeys.io.springo.repository.BookingRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository repository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllBooking_should_return_list_of_booking_when_success(){
        Booking booking = new Booking();
        booking.setCheckIn(LocalDate.parse("2024-06-01"));
        booking.setCheckOut(LocalDate.parse("2024-06-10"));
        booking.setGuestName("Mat Solar");

        Booking booking2 = new Booking();
        booking2.setCheckIn(LocalDate.parse("2024-05-01"));
        booking2.setCheckOut(LocalDate.parse("2024-09-10"));
        booking2.setGuestName("Jamal Mussolini");

        List<Booking> bookingList = Arrays.asList(booking, booking2);

        when(repository.findAll()).thenReturn(bookingList);

        List<Booking> resultList = bookingService.getAllBookings();

        assertEquals(bookingList.size(), resultList.size());
        assertTrue(
                resultList.stream().anyMatch(b->
                        bookingList.get(0).getGuestName().equals(b.getGuestName())
                )
        );
    }

    @Test
    public void createBooking_should_return_booking_when_repository_success_return_booking(){
        Booking booking = new Booking();
        booking.setCheckIn(LocalDate.parse("2024-06-01"));
        booking.setCheckOut(LocalDate.parse("2024-06-10"));
        booking.setGuestName("Mat Solar");

        when(repository.save(any(Booking.class)))
                .thenReturn(booking);

        // Act
        Booking savedBooking = bookingService.createBooking(booking);

        // Assert
        assertEquals(booking.getGuestName(), savedBooking.getGuestName());
        assertEquals(booking.getCheckIn(), savedBooking.getCheckIn());
    }
}