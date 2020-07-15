package playground;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class AgeCalculation {
    public static void main(String[] args) {
        LocalDate birthdate = LocalDate.of(1984, 11, 01);
        LocalDate today = LocalDate.now();
        Period p = Period.between(birthdate,today);
        System.out.printf("%d years, %d months, %d days%n",
                p.getYears(),p.getMonths(),p.getDays());
        System.out.println("total days :"+ ChronoUnit.DAYS.between(birthdate,today));

    }
}
