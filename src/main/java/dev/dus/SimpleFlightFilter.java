package dev.dus;

import java.util.List;

public interface SimpleFlightFilter {
    List<Flight> filterFlights(List<Flight> flights);
}
