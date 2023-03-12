package pathDecorator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

public class PathInterpreter implements PathModifier {
    public static final DateTimeFormatter DATE_TO_DAY1 = DateTimeFormatter.ofPattern("d");
    public static final DateTimeFormatter DATE_TO_DAY = DateTimeFormatter.ofPattern("dd");
    public static final DateTimeFormatter DATE_TO_MONTH = DateTimeFormatter.ofPattern("MM");
    public static final DateTimeFormatter DATE_TO_MONTH3 = DateTimeFormatter.ofPattern("MMM");
    public static final DateTimeFormatter DATE_TO_YEAR2 = DateTimeFormatter.ofPattern("yy");
    public static final DateTimeFormatter DATE_TO_YEAR4 = DateTimeFormatter.ofPattern("yyyy");

    @Override
    public String getFinalPath(String path) {

        //insert currentWeeksAndDays
        path = path
                .replaceAll("#~Ws00~", String.format("%2d", getWeeks()))
                .replaceAll("#~Ws~", String.valueOf(getWeeks()))
                .replaceAll("#~Ds000~", String.format("%3d", getDays()))
                .replaceAll("#~Ds~", String.valueOf(getDays()))
                .replaceAll("#~ws00\\+~", String.format("%2d", getWeeks() + 1))
                .replaceAll("#~ws\\+~", String.valueOf(getWeeks() + 1))
                .replaceAll("#~ws00-~", String.format("%2d", getWeeks() - 1))
                .replaceAll("#~ws-~", String.valueOf(getWeeks() - 1))
                .replaceAll("#~ds000\\+~", String.format("%3d", getDays() + 1))
                .replaceAll("#~ds\\+~", String.valueOf(getDays() + 1))
                .replaceAll("#~ds000-~", String.format("%3d", getDays() - 1))
                .replaceAll("#~ds-~", String.valueOf(getDays() - 1));

        //insert current Date
        LocalDate now = LocalDate.now();
        path = path.replaceAll("#~DATE1~", getDateFormat1().format(now))
                .replaceAll("#~DATE2~", getDateFormat2().format(now))
                .replaceAll("#~DATE-S~", DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(now))
                .replaceAll("#~DATE-M~", DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(now))
                .replaceAll("#~DAY~", DATE_TO_DAY.format(now))
                .replaceAll("#~DAY1~", DATE_TO_DAY1.format(now))
                .replaceAll("#~MONTH~", DATE_TO_MONTH.format(now))
                .replaceAll("#~MONTH3~", DATE_TO_MONTH3.format(now))
                .replaceAll("#~YEAR~", DATE_TO_YEAR2.format(now))
                .replaceAll("#~YEAR4~", DATE_TO_YEAR4.format(now));

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
        return DateTimeFormatter.ofPattern("yyyy-mm-dd");
    }

    @Override
    public DateTimeFormatter getDateFormat2() {
        return DateTimeFormatter.ofPattern("yyyy-mm-dd");
    }

}
