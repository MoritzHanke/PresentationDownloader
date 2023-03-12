package pathDecorator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class HolidayModifier implements PathModifier {
    PathModifier pathModifier;
    LocalDate holidayStart;
    LocalDate uniStart;
    long daysBetween;
    long weeksBetween;

    public HolidayModifier(PathModifier decorElement, String arg) {
        this.pathModifier = decorElement;
        String[] stringDates = arg.split("#");

        this.holidayStart = LocalDate.parse(stringDates[0]);
        this.uniStart = LocalDate.parse(stringDates[1]);

        LocalDate start = holidayStart.compareTo(LocalDate.now()) <= 0 ? holidayStart : LocalDate.now();
        LocalDate end = uniStart.compareTo(LocalDate.now()) <= 0 ? uniStart : LocalDate.now();

        daysBetween = ChronoUnit.DAYS.between(start, end);
        weeksBetween = ChronoUnit.WEEKS.between(start, end);
    }

    @Override
    public String getFinalPath(String path) {
        return pathModifier.getFinalPath(path);
    }

    @Override
    public long getWeeks() {
        return pathModifier.getWeeks() - weeksBetween;
    }

    @Override
    public long getDays() {
        return pathModifier.getDays() - daysBetween;
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
        return pathModifier.getDateFormat2();
    }
}
