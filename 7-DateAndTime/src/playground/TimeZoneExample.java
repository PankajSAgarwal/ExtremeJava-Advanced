package playground;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeZoneExample {
    public static void main(String[] args) {
        LocalDateTime leaving =
                LocalDateTime.of(2020, Month.JUNE,30,19,30);
        ZoneId leavingZone = ZoneId.of("America/Los_Angeles");
        ZonedDateTime departure = ZonedDateTime.of(leaving,leavingZone);

        ZoneId arrivingZone = ZoneId.of("Asia/Tokyo");
        ZonedDateTime arrival = departure.withZoneSameInstant(arrivingZone)
                                         .plusHours(10).plusMinutes(50);

        System.out.println("departure at " + departure);
        System.out.println("arrival at " + arrival);


    }
}
