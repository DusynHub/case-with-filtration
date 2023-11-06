package dev.dus;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterTooLongGroundTimeTest {

    @Test
    void when_empty_flight_list___then_return_empty_list() {
        FilterTooLongGroundTime filter = new FilterTooLongGroundTime(120);
        List<Flight> flights = new ArrayList<>();
        List<Flight> result = filter.filterFlights(flights);
        assertEquals(0, result.size());
    }


    @Test
    void when_no_one_flight_with_ground_time_lower_then_120_min__then_return_one_flight() {
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(3));
        Flight flightWithGroundTimeBiggerThan120Minutes = new Flight(Arrays.asList(segment1));

        List<Flight> flights = Arrays.asList(flightWithGroundTimeBiggerThan120Minutes);

        FilterTooLongGroundTime filter = new FilterTooLongGroundTime(120);

        List<Flight> result = filter.filterFlights(flights);
        List<Flight> expectedFlights = Collections.emptyList();

        assertEquals(0, result.size());
        assertEquals(expectedFlights, result);
    }



    @Test
    void when_at_least_one_flight_with_ground_time_lower_then_120_min__then_return_one_flight() {
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        Flight flightWithGroundTimeEqualThan120Minutes = new Flight(Arrays.asList(segment1));

        Segment segment2 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(3));
        Flight flightWithGroundTimeBiggerThan120Minutes = new Flight(Arrays.asList(segment2));

        List<Flight> flights = Arrays.asList(flightWithGroundTimeEqualThan120Minutes,
                                             flightWithGroundTimeBiggerThan120Minutes);

        FilterTooLongGroundTime filter = new FilterTooLongGroundTime(120);

        List<Flight> result = filter.filterFlights(flights);
        List<Flight> expectedFlights = Arrays.asList(flightWithGroundTimeEqualThan120Minutes);

        assertEquals(1, result.size());
        assertEquals(expectedFlights, result);
    }

    @Test
    void when_two_flights_with_ground_time_lower_then_120_min__then_return_two_flights() {
        // Arrange
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4));
        Flight flightWithGroundTimeLowerThan180Minutes = new Flight(Arrays.asList(segment1, segment2));

        Segment segment3 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment segment4 = new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(8));
        Flight flightWithGroundTimeBiggerThan180Minutes = new Flight(Arrays.asList(segment3, segment4));

        Segment segment5 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Flight flightWithGroundTimeLowerThan180Minutes2 = new Flight(Arrays.asList(segment5));

        List<Flight> flights = Arrays.asList(
                flightWithGroundTimeLowerThan180Minutes,
                flightWithGroundTimeBiggerThan180Minutes,
                flightWithGroundTimeLowerThan180Minutes2);

        FilterTooLongGroundTime filter = new FilterTooLongGroundTime(180);

        List<Flight> result = filter.filterFlights(flights);

        List<Flight> expectedFlights = Arrays.asList(flightWithGroundTimeLowerThan180Minutes, flightWithGroundTimeLowerThan180Minutes2);
        assertEquals(2, result.size());
        assertEquals(expectedFlights, result);
    }

    @Test
    void when_all_flights_with_ground_time_more_then_120_min___then_return_empty() {
        // Arrange
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(7));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4));
        Flight flightWithGroundTimeBiggerThan180Minutes1 = new Flight(Arrays.asList(segment1, segment2));

        Segment segment3 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(8));
        Segment segment4 = new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5));
        Flight flightWithGroundTimeBiggerThan180Minutes2 = new Flight(Arrays.asList(segment3, segment4));

        Segment segment5 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(10));
        Flight flightWithGroundTimeBiggerThan180Minutes3 = new Flight(Arrays.asList(segment5));

        List<Flight> flights = Arrays.asList(
                flightWithGroundTimeBiggerThan180Minutes1,
                flightWithGroundTimeBiggerThan180Minutes2,
                flightWithGroundTimeBiggerThan180Minutes3);

        FilterTooLongGroundTime filter = new FilterTooLongGroundTime(180);

        List<Flight> result = filter.filterFlights(flights);
        List<Flight> expectedFlights = Collections.emptyList();

        assertEquals(0, result.size());
        assertEquals(expectedFlights, result);
    }
}