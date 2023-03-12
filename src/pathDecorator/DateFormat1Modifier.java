package pathDecorator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat1Modifier implements PathModifier {
    PathModifier pathModifier;
    String pattern;

    public DateFormat1Modifier(PathModifier decorElement, String arg) {
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
        return DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public DateTimeFormatter getDateFormat2() {
        return pathModifier.getDateFormat2();
    }
}
