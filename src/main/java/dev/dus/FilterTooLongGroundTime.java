package dev.dus;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class FilterTooLongGroundTime implements SimpleFlightFilter {
    private final long maxGroundTimeMinutes;

    public FilterTooLongGroundTime(long maxGroundTimeInMinutes) {
        this.maxGroundTimeMinutes = maxGroundTimeInMinutes;
    }

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    long totalGroundTime = 0;
                    for (int i = 0; i < segments.size(); i++) {
                        long groundTime = Duration.between(segments.get(i).getDepartureDate(), segments.get(i).getArrivalDate()).toMinutes();
                        totalGroundTime = totalGroundTime + groundTime;
                    }
                    return totalGroundTime <= maxGroundTimeMinutes;
                })
                .collect(Collectors.toList());
    }


}
