package pathDecorator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PathInterpreter implements PathModifier {
    public static final DateTimeFormatter DATE_TO_DAY = DateTimeFormatter.ofPattern("d");
    public static final DateTimeFormatter DATE_TO_DAY00 = DateTimeFormatter.ofPattern("dd");
    public static final DateTimeFormatter DATE_TO_MONTH0 = DateTimeFormatter.ofPattern("M");
    public static final DateTimeFormatter DATE_TO_MONTH00 = DateTimeFormatter.ofPattern("MM");
    public static final DateTimeFormatter DATE_TO_MONTH3 = DateTimeFormatter.ofPattern("MMM");
    public static final DateTimeFormatter DATE_TO_YEAR00 = DateTimeFormatter.ofPattern("yy");
    public static final DateTimeFormatter DATE_TO_YEAR0000 = DateTimeFormatter.ofPattern("yyyy");

    @Override
    public String getFinalPath(String path) {

        //insert currentWeeksAndDays
        path = path
                .replaceAll("#~ws~", String.valueOf(getWeeks()))
                .replaceAll("#~ws\\+~", String.valueOf(getWeeks() + 1))
                .replaceAll("#~ws-~", String.valueOf(getWeeks() - 1))
                .replaceAll("#~ws00~", String.format("%2d", getWeeks()))
                .replaceAll("#~ws00\\+~", String.format("%2d", getWeeks() + 1))
                .replaceAll("#~ws00-~", String.format("%2d", getWeeks() - 1))
                .replaceAll("#~ds~", String.valueOf(getDays()))
                .replaceAll("#~ds\\+~", String.valueOf(getDays() + 1))
                .replaceAll("#~ds-~", String.valueOf(getDays() - 1))
                .replaceAll("#~ds00~", String.format("%2d", getDays()))
                .replaceAll("#~ds00\\+~", String.format("%2d", getDays() + 1))
                .replaceAll("#~ds00-~", String.format("%2d", getDays() - 1))
                .replaceAll("#~ds000~", String.format("%3d", getDays()))
                .replaceAll("#~ds000\\+~", String.format("%3d", getDays() + 1))
                .replaceAll("#~ds000-~", String.format("%3d", getDays() - 1));

        //insert current Date
        LocalDate now = LocalDate.now();
        path = path.replaceAll("#~DATE1~", getDateFormat1().format(now))
                .replaceAll("#~DATE2~", getDateFormat2().format(now))
                .replaceAll("#~DAY~", DATE_TO_DAY.format(now))
                .replaceAll("#~DAY00~", DATE_TO_DAY00.format(now))
                .replaceAll("#~MONTH~", DATE_TO_MONTH0.format(now))
                .replaceAll("#~MONTH00~", DATE_TO_MONTH00.format(now))
                .replaceAll("#~MONTH3~", DATE_TO_MONTH3.format(now))
                .replaceAll("#~YEAR00~", DATE_TO_YEAR00.format(now))
                .replaceAll("#~YEAR0000~", DATE_TO_YEAR0000.format(now));

        return path;
    }

    @Override
    public long getWeeks() {
        return ChronoUnit.WEEKS.between(getStartDate(), LocalDate.now());
    }

    @Override
    public long getDays() {
        return ChronoUnit.DAYS.between(getStartDate(), LocalDate.now());
    }

    @Override
    public LocalDate getStartDate() {
        return LocalDate.now();
    }

    @Override
    public DateTimeFormatter getDateFormat1() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    @Override
    public DateTimeFormatter getDateFormat2() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

}
