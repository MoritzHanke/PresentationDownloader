package pathDecorator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat2Modifier implements PathModifier {
    PathModifier pathModifier;
    String pattern;

    public DateFormat2Modifier(PathModifier decorElement, String arg) {
        this.pathModifier = decorElement;
        this.pattern = arg;
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
        return pathModifier.getStartDate();
    }

    @Override
    public DateTimeFormatter getDateFormat1() {
        return pathModifier.getDateFormat1();
    }

    @Override
    public DateTimeFormatter getDateFormat2() {
        return DateTimeFormatter.ofPattern(pattern);
    }
}
