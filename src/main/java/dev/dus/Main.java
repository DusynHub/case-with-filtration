package dev.dus;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> testFlightList = FlightBuilder.createFlights();
        System.out.printf("Исходные данные");
        System.out.println();
        testFlightList.forEach((System.out::println));

        DynamicFlightFilter dynamicFlightFilter = DynamicFlightFilter.builder()
                .andFilter(new FilterArrivalBeforeDeparture())
                .build();
        System.out.println("После фильтрации с FilterArrivalBeforeDeparture");
        dynamicFlightFilter.filter(testFlightList).forEach(System.out::println);

        DynamicFlightFilter dynamicFlightFilter2 = DynamicFlightFilter.builder()
                .andFilter(new FilterDepartureBeforeCurrentTime())
                .build();
        System.out.println("После фильтрации с FilterDepartureBeforeCurrentTime");
        dynamicFlightFilter2.filter(testFlightList).forEach(System.out::println);

        int maxGroundTimeInMinutes = 120;
        DynamicFlightFilter dynamicFlightFilter3 = DynamicFlightFilter.builder()
                .andFilter(new FilterTooLongGroundTime(maxGroundTimeInMinutes))
                .build();
        System.out.println(String.format("После фильтрации " +
                "с FilterTooLongGroundTime( maxGroundTimeInMinutes = %s))", maxGroundTimeInMinutes));
        dynamicFlightFilter3.filter(testFlightList).forEach(System.out::println);

        DynamicFlightFilter dynamicFlightFilter4 = DynamicFlightFilter.builder()
                .andFilter(new FilterTooLongGroundTime(maxGroundTimeInMinutes))
                .andFilter(new FilterArrivalBeforeDeparture())
                .build();
        System.out.println(
                String.format("После фильтрации с TooLongGroundTimeSimpleFlightFilter( maxGroundTimeInMinutes = %s)) " +
                        "и FilterArrivalBeforeDeparture", maxGroundTimeInMinutes));
        dynamicFlightFilter4.filter(testFlightList).forEach(System.out::println);
    }
}
