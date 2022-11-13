package it.unibo.nestedenum;

import java.util.Comparator;
//import java.util.Locale;
//import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */


public final class MonthSorterNested implements MonthSorter {

    private static final Comparator<String> BY_DAYS = new SortByDays();
    private static final Comparator<String> BY_ORDER = new SortByMonthOrder();

    @Override
    public Comparator<String> sortByDays() {
        return BY_DAYS;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return BY_ORDER;
    }

    private enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(final int days) {
            this.days = days;
        }

        static Month fromString(String month){
            Month temp = null;
            int count = 0;
            for (Month name : values() ){
                if (name.name().toLowerCase().contains(month.toLowerCase())){
                    temp = name;
                    count++;
                }
            }
            if (count == 1){
                return temp;
            }
           throw new IllegalArgumentException("No matching months for " + month);    


        }
        
    }
    private static class SortByDays implements Comparator<String> {
        @Override
        public int compare(final String s1, final String s2) {
            final var m1 = Month.fromString(s1);
            final var m2 = Month.fromString(s2);
            return Integer.compare(m1.days, m2.days);
        }
    }

    private static class SortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(final String s1, final String s2) {
            return Month.fromString(s1).compareTo(Month.fromString(s2));
        }
    }
}


