package pathDecorator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface PathModifier {
    public String getFinalPath(String path);

    long getWeeks();
    long getDays();
    LocalDate getStartDate();
    DateTimeFormatter getDateFormat1();
    DateTimeFormatter getDateFormat2();

}
