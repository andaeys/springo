package andaeys.io.springo.controller;

import andaeys.io.springo.entity.Booking;
import andaeys.io.springo.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @InjectMocks
    private BookingController controller;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @WithMockUser
    public void getAllBooking_should_return_booking_list_response_when_success() throws Exception{
        Booking booking = new Booking();
        booking.setCheckIn(LocalDate.parse("2024-05-01"));
        booking.setCheckOut(LocalDate.parse("2024-09-10"));
        booking.setGuestName("Jamal Mussolini");

        List<Booking> bookingList = Arrays.asList(booking);
        when(bookingService.getAllBookings()).thenReturn(bookingList);

        String bookingJson = objectMapper.writeValueAsString(bookingList);

        mockMvc.perform(get("/api/bookings/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(bookingJson));
    }

    @Test
    @WithMockUser
    public void createBooking_shouldReturnBooking_whenSuccess() throws Exception {
//        Booking booking = new Booking();
//        booking.setCheckIn(LocalDate.parse("2024-05-01"));
//        booking.setCheckOut(LocalDate.parse("2024-09-10"));
//        booking.setGuestName("Jamal Mussolini");
//
//        when(bookingService.createBooking(any(Booking.class))).thenReturn(booking);
//
//        String bookingJson = objectMapper.writeValueAsString(booking);
//
//        mockMvc.perform(post("/api/bookings")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(bookingJson))
//                .andExpect(status().isOk());
    }
}
