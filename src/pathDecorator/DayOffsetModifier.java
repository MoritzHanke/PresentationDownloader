package pathDecorator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DayOffsetModifier implements PathModifier {
    PathModifier pathModifier;
    long dayOffset;

    public DayOffsetModifier(PathModifier decorElement, String arg) {
        this.pathModifier = decorElement;
        dayOffset = Long.parseLong(arg);
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
        return pathModifier.getStartDate().minusDays(dayOffset);
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
