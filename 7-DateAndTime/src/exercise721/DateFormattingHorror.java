package exercise721;

import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateFormattingHorror {
    //private final DateFormat format =
      //      new SimpleDateFormat("dd.MM.yyyy");

    private final DateTimeFormatter format =
            DateTimeFormatter.ofPattern("[d.M.yyyy][d.M.yy]");

    public LocalDate parse(String textDate) {
        return LocalDate.parse(textDate,format);
    }

}
