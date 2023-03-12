package pathDecorator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StartDateModifier implements PathModifier {
    PathModifier pathModifier;
    LocalDate startDate;

    public StartDateModifier(PathModifier decorElement, String arg) {
        this.pathModifier = decorElement;
        this.startDate = LocalDate.parse(arg);
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
        return startDate;
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
