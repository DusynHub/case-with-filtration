package dev.dus;

import java.util.List;
import java.util.stream.Collectors;

public class FilterArrivalBeforeDeparture implements SimpleFlightFilter {
    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}