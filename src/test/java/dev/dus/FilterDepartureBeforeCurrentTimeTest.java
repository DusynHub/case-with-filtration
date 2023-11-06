package dev.dus;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterDepartureBeforeCurrentTimeTest {

    @Test
    void when_empty_flight_list___then_return_empty_list() {
        FilterDepartureBeforeCurrentTime filter = new FilterDepartureBeforeCurrentTime();
        List<Flight> flights = new ArrayList<>();
        List<Flight> result = filter.filterFlights(flights);
        assertTrue(result.isEmpty());
    }

    @Test
    void when_no_one_flight_departure_after_now__then_return_empty() {
        FilterDepartureBeforeCurrentTime filter = new FilterDepartureBeforeCurrentTime();
        LocalDateTime now = LocalDateTime.now();
        List<Segment> segments1 = Arrays.asList(new Segment(now.minusHours(10), now.plusHours(2)));
        List<Segment> segments2 = Arrays.asList(new Segment(now.minusHours(3), now.plusHours(1)));
        List<Flight> flights = Arrays.asList(new Flight(segments1), new Flight(segments2));

        List<Flight> result = filter.filterFlights(flights);
        List<Flight> expectedFlights = Collections.emptyList();

        assertEquals(expectedFlights, result);
    }


    @Test
    void when_at_least_one_flight_departure_after_now__then_return_one() {
        FilterDepartureBeforeCurrentTime filter = new FilterDepartureBeforeCurrentTime();
        LocalDateTime now = LocalDateTime.now();
        List<Segment> segments1 = Arrays.asList(new Segment(now.minusHours(2), now.plusHours(1)));
        List<Segment> segments2 = Arrays.asList(new Segment(now.plusHours(2), now.plusHours(3)));
        List<Flight> flights = Arrays.asList(new Flight(segments1), new Flight(segments2));

        List<Flight> result = filter.filterFlights(flights);
        List<Flight> expectedFlights = Arrays.asList(new Flight(segments2));

        assertEquals(expectedFlights, result);
    }

    @Test
    void when_all_flight_departure_after_now__then_return_all() {
        FilterDepartureBeforeCurrentTime filter = new FilterDepartureBeforeCurrentTime();
        LocalDateTime now = LocalDateTime.now();
        List<Segment> segments1 = Arrays.asList(new Segment(now.plusHours(2), now.plusHours(10)));
        List<Segment> segments2 = Arrays.asList(new Segment(now.plusHours(3), now.plusHours(13)));
        List<Flight> flights = Arrays.asList(new Flight(segments1), new Flight(segments2));

        List<Flight> result = filter.filterFlights(flights);
        List<Flight> expectedFlights = Arrays.asList(new Flight(segments1), new Flight(segments2));

        assertEquals(expectedFlights, result);
    }
}