package dev.dus;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterArrivalBeforeDepartureTest {

    @Test
    void when_empty_flight_list___then_return_empty_list() {
        FilterArrivalBeforeDeparture filter = new FilterArrivalBeforeDeparture();
        List<Flight> flights = new ArrayList<>();
        List<Flight> result = filter.filterFlights(flights);
        assertTrue(result.isEmpty());
    }

    @Test
    void when_no_one_flight_with_arrival_is_after_departure___then_return_one_flight() {
        FilterArrivalBeforeDeparture filter = new FilterArrivalBeforeDeparture();
        List<Segment> segments1 = Arrays.asList(
                new Segment(LocalDateTime.of(2022, 1, 1, 10, 0),
                        LocalDateTime.of(2022, 1, 1, 1, 0)));
        List<Segment> segments2 = Arrays.asList(
                new Segment(LocalDateTime.of(2022, 1, 2, 10, 0),
                        LocalDateTime.of(2022, 1, 2, 1, 0)));

        Flight arrivalIsBeforeDeparture1 = new Flight(segments1);
        Flight arrivalIsBeforeDeparture2 = new Flight(segments2);


        List<Flight> flights = Arrays.asList(
                arrivalIsBeforeDeparture1,
                arrivalIsBeforeDeparture2
        );

        List<Flight> result = filter.filterFlights(flights);
        List<Flight> expectedFilteredFlights = Collections.emptyList();

        assertEquals(expectedFilteredFlights, result);
    }

    @Test
    void when_at_least_one_flight_with_arrival_is_after_departure___then_return_one_flight() {
        FilterArrivalBeforeDeparture filter = new FilterArrivalBeforeDeparture();
        List<Segment> segments1 = Arrays.asList(
                new Segment(LocalDateTime.of(2022, 1, 1, 10, 0),
                            LocalDateTime.of(2022, 1, 1, 18, 0)));
        List<Segment> segments2 = Arrays.asList(
                new Segment(LocalDateTime.of(2022, 1, 2, 10, 0),
                            LocalDateTime.of(2022, 1, 1, 18, 0)));

        Flight arrivalIsAfterDeparture = new Flight(segments1);
        Flight arrivalIsBeforeDeparture = new Flight(segments2);


        List<Flight> flights = Arrays.asList(
                arrivalIsAfterDeparture,
                arrivalIsBeforeDeparture
        );

        List<Flight> result = filter.filterFlights(flights);
        List<Flight> expectedFilteredFlights = Arrays.asList(
                arrivalIsAfterDeparture);

        assertEquals(expectedFilteredFlights, result);
    }

    @Test
    void when_two_flights_with_arrival_is_after_departure__then_return_two_flights() {
        FilterArrivalBeforeDeparture filter = new FilterArrivalBeforeDeparture();
        List<Segment> segments1 = Arrays.asList(
                new Segment(LocalDateTime.of(2022, 1, 1, 10, 0),
                            LocalDateTime.of(2022, 1, 1, 12, 0)),
                new Segment(LocalDateTime.of(2022, 1, 1, 13, 0),
                            LocalDateTime.of(2022, 1, 1, 11, 0)));

        List<Segment> segments2 = Arrays.asList(
                new Segment(LocalDateTime.of(2022, 1, 2, 10, 0),
                            LocalDateTime.of(2022, 1, 2, 12, 0)));

        List<Segment> segments3 = Arrays.asList(
                new Segment(LocalDateTime.of(2022, 2, 2, 10, 0),
                            LocalDateTime.of(2022, 2, 2, 12, 0)));

        Flight arrivalIsBeforeDeparture = new Flight(segments1);
        Flight arrivalIsAfterDeparture1 = new Flight(segments2);
        Flight arrivalIsAfterDeparture2 = new Flight(segments3);

        List<Flight> flights = Arrays.asList(
                arrivalIsBeforeDeparture,
                arrivalIsAfterDeparture1,
                arrivalIsAfterDeparture2);

        List<Flight> result = filter.filterFlights(flights);
        List<Flight> expectedFilteredFlights = Arrays.asList(
                arrivalIsAfterDeparture1,
                arrivalIsAfterDeparture2);

        assertEquals(expectedFilteredFlights.size(), result.size());
        assertEquals(expectedFilteredFlights, result);
    }

    @Test
    void when_arrival_and_departuree_date_same___then_return_empty() {
        FilterArrivalBeforeDeparture filter = new FilterArrivalBeforeDeparture();

        List<Segment> segments1 = Arrays.asList(
                new Segment(LocalDateTime.of(2022, 2, 2, 10, 0), LocalDateTime.of(2022, 2, 2, 10, 0)));

        Flight flight = new Flight(segments1);

        List<Flight> flights = Arrays.asList(
                flight);

        List<Flight> result = filter.filterFlights(flights);
        List<Flight> expectedFilteredFlights = Collections.emptyList();

        assertEquals(expectedFilteredFlights.size(), result.size());
        assertEquals(expectedFilteredFlights, result);
    }
}