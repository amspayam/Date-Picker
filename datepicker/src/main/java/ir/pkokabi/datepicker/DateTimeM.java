package ir.pkokabi.datepicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by p.kokabi on 1/2/2018.
 */

public class DateTimeM {

    private String id, dateTimeName;

    DateTimeM() {
    }

    private DateTimeM(String id, String dateTimeName) {
        this.id = id;
        this.dateTimeName = dateTimeName;
    }

    /*Setters=====================================================================================*/
    public String getId() {
        return id;
    }

    /*Getters=====================================================================================*/
    String getDateTimeName() {
        return dateTimeName;
    }

    /*Methods=====================================================================================*/
    ArrayList<DateTimeM> getDateList(int indexDateFromToday) {
        ArrayList<DateTimeM> dateList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, indexDateFromToday + i);
            Locale loc = new Locale("en_US");
            SolarCalendar sc = new SolarCalendar(calendar);
            dateList.add(new DateTimeM(sc.getYear() + "/" + sc.getMonth() + "/" + sc.getDate(), sc.getStrWeekDay()
                    + " " + String.format(loc, "%01d", sc.getDate())
                    + " " + sc.getStrMonth()));
        }
        return dateList;
    }

    ArrayList<DateTimeM> getTimeList(int minHour, int maxHour) {
        ArrayList<DateTimeM> hourList = new ArrayList<>();
        hourList.add(new DateTimeM("-2", ""));
        hourList.add(new DateTimeM("-1", ""));
        for (int i = minHour; i <= maxHour; i++)
            hourList.add(new DateTimeM(String.valueOf(i), String.valueOf(i) + ":" + "00"));
        hourList.add(new DateTimeM("1000", ""));
        hourList.add(new DateTimeM("1001", ""));
        return hourList;
    }

}
