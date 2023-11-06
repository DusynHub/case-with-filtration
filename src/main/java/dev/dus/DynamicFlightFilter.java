package dev.dus;

import java.util.ArrayList;
import java.util.List;

public class DynamicFlightFilter {
    private final List<SimpleFlightFilter> filters;

    public DynamicFlightFilter(List<SimpleFlightFilter> filters) {
        this.filters = filters;
    }

    public static DynamicFlightFilterBuilder builder() {
        return new DynamicFlightFilterBuilder();
    }

    public List<Flight> filter(List<Flight> flights) {
        for (SimpleFlightFilter filter : filters) {
            flights = filter.filterFlights(flights);
        }
        return flights;
    }

    public static class DynamicFlightFilterBuilder {
        private List<SimpleFlightFilter> filters = new ArrayList<>();

        DynamicFlightFilterBuilder() {
        }

        public DynamicFlightFilter build() {
            return new DynamicFlightFilter(this.filters);
        }

        public DynamicFlightFilterBuilder andFilter(SimpleFlightFilter filter) {
            filters.add(filter);
            return this;
        }


        public String toString() {
            return "DynamicFlightFilter.DynamicFlightFilterBuilder(filters=" + this.filters + ")";
        }
    }
}