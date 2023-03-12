package pathDecorator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class WeekOffsetModifier implements PathModifier {
    PathModifier pathModifier;
    long weekOffset;

    public WeekOffsetModifier(PathModifier decorElement, String arg) {
        this.pathModifier = decorElement;
        weekOffset = Long.parseLong(arg);
    }

    @Override
    public String getFinalPath(String path) {
        return pathModifier.getFinalPath(path);
    }

    @Override
    public long getWeeks() {
        return pathModifier.getWeeks();
    }

    @Override
    public long getDays() {
        return pathModifier.getDays();
    }

    @Override
    public LocalDate getStartDate() {
        return pathModifier.getStartDate().minusWeeks(weekOffset);
    }

    @Override
    public DateTimeFormatter getDateFormat1() {
        return pathModifier.getDateFormat1();
    }

    @Override
    public DateTimeFormatter getDateFormat2() {
        return pathModifier.getDateFormat2();
    }
}
